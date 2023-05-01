package cz.uhk.umte.di.repositories

import cz.uhk.umte.data.remote.service.GenshinDevService


class GenshinDevRepository(
    private val api: GenshinDevService
) {
    suspend fun fetchCharactersIDs() =
        api.fetchCharactersIDs()

    suspend fun fetchAllCharacters() =
        api.fetchAllCharacters()

    suspend fun fetchCharacterInfo(charName: String) =
        api.fetchCharacter(charName)

    suspend fun fetchWeaponsIDs() =
        api.fetchWeaponsIDs()

    suspend fun fetchAllWeapons() =
        api.fetchAllWeapons()

    suspend fun fetchWeaponByName(wepID: String) =
        api.fetchWeaponByName(wepID)
}