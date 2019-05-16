package hu.ait.genonepokedex.network

import hu.ait.genonepokedex.data.PokemonResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    @GET("/api/v2/pokemon/{name}")
    fun getPokeDetails(@Path("name") name: String) : Call<PokemonResults>
}