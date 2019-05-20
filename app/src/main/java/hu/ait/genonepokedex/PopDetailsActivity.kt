package hu.ait.genonepokedex

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import hu.ait.genonepokedex.data.PokemonResults
import hu.ait.genonepokedex.network.PokemonAPI
import kotlinx.android.synthetic.main.activity_pop_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

            }
        })
    }
}
