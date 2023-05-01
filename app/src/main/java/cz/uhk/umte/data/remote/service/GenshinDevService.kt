package cz.uhk.umte.data.remote.service

import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GenshinDevService {

    @GET("characters")
    suspend fun fetchCharactersIDs(): List<String>

    @GET("characters/all")
    suspend fun fetchAllCharacters(): List<CharacterInfoResponse>

    @GET("characters/{charName}")
    suspend fun fetchCharacter(
        @Path("charName") charName: String
    ): CharacterInfoResponse?

    @GET("weapons")
    suspend fun fetchWeaponsIDs(): List<String>

    @GET("weapons/all")
    suspend fun fetchAllWeapons(): List<WeaponInfoResponse>

    @GET("weapons/{wepID}")
    suspend fun fetchWeaponByName(
        @Path("wepID") wepID: String
    ): WeaponInfoResponse?
}