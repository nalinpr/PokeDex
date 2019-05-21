package hu.ait.genonepokedex

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import hu.ait.genonepokedex.data.PokemonResults
import hu.ait.genonepokedex.network.PokemonAPI
import kotlinx.android.synthetic.main.activity_pop_details.*
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import android.content.Intent
import hu.ait.genonepokedex.data.TypeResults


class PopDetailsActivity : Activity() {

    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_details)
        setWindow()

        var num = ""

        if (intent.extras.containsKey(ScrollingActivity.POKE_NUM)) {
            num = intent.getStringExtra(ScrollingActivity.POKE_NUM)
        }

        pokeCall(num)

        btnBulb.setOnClickListener {
            val url = "https://bulbapedia.bulbagarden.net/wiki/${name}_(Pok%C3%A9mon)"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }


    }

    private fun setWindow() {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = (dm.widthPixels * 0.9).toInt()
        val height = (dm.heightPixels * 0.8).toInt()

        window.setLayout(width, height)
    }


    private fun pokeCall(num: String) {
        val HOST_URL = "https://pokeapi.co/"

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonAPI = retrofit.create(PokemonAPI::class.java)

        val pokemonResultCall = pokemonAPI.getPokeDetails(num)

        val baos = ByteArrayOutputStream()
        val imageInBytes = baos.toByteArray()
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val pokeImagesRef = storageRef.child("pokeImages")
        var file: String
        if (num.toInt() < 10){
            file = "00"+num
        }
        else if (num.toInt() < 100){
            file = "0"+num
        }
        else{
            file = num
        }
        val imageRef = pokeImagesRef.child("$file.png")
        var typeArray = ArrayList<String>()


        pokemonResultCall.enqueue(object : Callback<PokemonResults> {

            override fun onFailure(call: Call<PokemonResults>, t: Throwable) {
                tvPokeName.text = t.message
            }

            override fun onResponse(call: Call<PokemonResults>, response: Response<PokemonResults>) {
                val pokemonResult = response.body()
                tvPokeName.text = pokemonResult?.name?.capitalize()
                name = pokemonResult?.name?.capitalize().toString()

                val types = pokemonResult?.types
                tvTypeResult.append(types?.get(0)?.type?.name?.capitalize())
                typeArray.add(types?.get(0)?.type?.name!!)

                for (i in 1 until types.size) {
                    tvTypeResult.append(", ")
                    tvTypeResult.append(types[i].type?.name?.capitalize())
                    typeArray.add(types[i].type?.name!!)
                }
              
                typeCall(typeArray, pokemonAPI)

                imageRef.downloadUrl.addOnCompleteListener(object: OnCompleteListener<Uri> {
                    override fun onComplete(task: Task<Uri>) {
                        Glide.with(this@PopDetailsActivity)
                            .load(task.result.toString()).into(ivPokeImage)
                    }
                })
            }

        })

    }
  
  private fun typeCall(typeArray: ArrayList<String>, pokemonAPI: PokemonAPI) {
        for (i in 0 until typeArray.size) {
            val type = typeArray.get(i)
            val typeResultCall = pokemonAPI.getPokeType(type)

            typeResultCall.enqueue(object : Callback<TypeResults> {
                override fun onFailure(call: Call<TypeResults>, t: Throwable) {
                }

                override fun onResponse(call: Call<TypeResults>, response: Response<TypeResults>) {
                    val typeResult = response.body()

                    val vulnerable = typeResult?.damage_relations?.double_damage_from
                    val effective = typeResult?.damage_relations?.double_damage_to

                    for (j in 0 until vulnerable?.size!!) {
                        val checkString = tvVulnerableResults.text
                        val typeName = vulnerable[j].name?.capitalize()

                        if (typeName.toString() !in checkString) {
                            tvVulnerableResults.append(typeName)
                            tvVulnerableResults.append(", ")
                        }

                    }

                    for (j in 0 until effective?.size!!) {
                        val checkString = tvEffectiveResults.text
                        val typeName = effective[j].name?.capitalize()

                        if (typeName.toString() !in checkString) {
                            tvEffectiveResults.append(effective[j].name?.capitalize())
                            tvEffectiveResults.append(", ")
                        }

                    }

                }

            })
        }


    }
}
