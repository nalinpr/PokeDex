package hu.ait.genonepokedex.network

import hu.ait.genonepokedex.data.PokemonResults
import hu.ait.genonepokedex.data.TypeResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    @GET("/api/v2/pokemon/{name}")
    fun getPokeDetails(@Path("name") name: String) : Call<PokemonResults>

    @GET("/api/v2/type/{type}")
    fun getPokeType(@Path("type") type: String) : Call<TypeResults>
}