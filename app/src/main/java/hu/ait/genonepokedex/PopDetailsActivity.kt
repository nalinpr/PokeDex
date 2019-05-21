<<<<<<< HEAD
package hu.ait.genonepokedex

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.net.Uri
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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

class PopDetailsActivity : Activity() {

    private val HOST_URL = "https://pokeapi.co/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_details)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = (dm.widthPixels * 0.9).toInt()
        val height = (dm.heightPixels *0.8).toInt()

        window.setLayout(width, height)

        pokeCall()

    }

    private fun pokeCall() {
        var name = ""
        var num = ""
        var imgUrl = ""

        if (intent.extras.containsKey(ScrollingActivity.POKE_NAME) && intent.extras.containsKey(ScrollingActivity.IMG_URL)) {
            name = intent.getStringExtra(ScrollingActivity.POKE_NAME)
            num = intent.getStringExtra(ScrollingActivity.POKE_NUM)
            imgUrl = intent.getStringExtra(ScrollingActivity.IMG_URL)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonAPI = retrofit.create(PokemonAPI::class.java)

        val pokemonResultCall = pokemonAPI.getPokeDetails(name)

        val baos = ByteArrayOutputStream()
        val imageInBytes = baos.toByteArray()
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val pokeImagesRef = storageRef.child("pokeImages")
        val imageRef = pokeImagesRef.child("001.png")


        pokemonResultCall.enqueue(object : Callback<PokemonResults> {

            override fun onFailure(call: Call<PokemonResults>, t: Throwable) {
                tvPokeName.text = t.message
            }

            override fun onResponse(call: Call<PokemonResults>, response: Response<PokemonResults>) {
                val pokemonResult = response.body()
                tvPokeName.text = name.capitalize()

                val types = pokemonResult?.types
                types?.forEach {
                    tvTypeResult.append(it.type?.name)
                    tvTypeResult.append(", ")
                }

                imageRef.downloadUrl.addOnCompleteListener(object: OnCompleteListener<Uri> {
                    override fun onComplete(task: Task<Uri>) {
                        Glide.with(this@PopDetailsActivity)
                            .load(task.result.toString()).into(ivPokeImage)
                    }
                })



            }
        })
    }
}
=======
package hu.ait.genonepokedex

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.net.Uri
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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

class PopDetailsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_details)
        setWindow()

        var num = ""
        var imgUrl = ""

        if (intent.extras.containsKey(ScrollingActivity.POKE_NUM) && intent.extras.containsKey(ScrollingActivity.IMG_URL)) {
            num = intent.getStringExtra(ScrollingActivity.POKE_NUM)
            imgUrl = intent.getStringExtra(ScrollingActivity.IMG_URL)
        }

        pokeCall(num)

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
        val imageRef = pokeImagesRef.child("001.png")


        pokemonResultCall.enqueue(object : Callback<PokemonResults> {

            override fun onFailure(call: Call<PokemonResults>, t: Throwable) {
                tvPokeName.text = t.message
            }

            override fun onResponse(call: Call<PokemonResults>, response: Response<PokemonResults>) {
                val pokemonResult = response.body()
                tvPokeName.text = pokemonResult?.name?.capitalize()

                val types = pokemonResult?.types
                tvTypeResult.append(types?.get(0)?.type?.name?.capitalize())

                for (i in 1 until types?.size!!) {
                    tvTypeResult.append(", ")
                    tvTypeResult.append(types?.get(i)?.type?.name?.capitalize())
                }

                imageRef.putBytes(imageInBytes)
                    .addOnFailureListener { exception ->
                        Toast.makeText(this@PopDetailsActivity, exception.message, Toast.LENGTH_SHORT).show()
                    }.addOnSuccessListener { taskSnapshot ->
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                        imageRef.downloadUrl.addOnCompleteListener(object: OnCompleteListener<Uri> {
                            override fun onComplete(task: Task<Uri>) {
                                Glide.with(this@PopDetailsActivity)
                                    .load(task.result.toString()).into(ivPokeImage)
                            }
                        })
                    }



            }
        })
    }
}
>>>>>>> a3a2121258dda0de7705e15067b22d7315f718d9
