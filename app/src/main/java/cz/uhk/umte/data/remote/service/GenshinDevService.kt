package cz.uhk.umte.data.remote.service

import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenshinDevService {

    @GET("characters")
    suspend fun fetchAllCharacter(
    ): List<String>

    @GET("characters/{charName}")
    suspend fun fetchCharacter(
        @Path("charName") charName: String
    ): CharacterInfoResponse?


}