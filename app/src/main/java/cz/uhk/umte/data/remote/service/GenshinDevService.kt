package cz.uhk.umte.data.remote.service

import cz.uhk.umte.data.remote.response.ArtifactsListResponse
import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GenshinDevService {

    @GET("characters")
    suspend fun fetchAllCharacters(): List<String>

    @GET("characters/{charName}")
    suspend fun fetchCharacter(
        @Path("charName") charName: String
    ): CharacterInfoResponse?

    @GET("artifacts")
    suspend fun fetchAllArtifacts(): List<String>

    @GET("characters/all")
    suspend fun fetchAllArtifactsAsObject(): ArtifactsListResponse?
}