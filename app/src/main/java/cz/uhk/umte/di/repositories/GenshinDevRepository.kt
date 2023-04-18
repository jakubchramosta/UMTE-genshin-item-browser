package cz.uhk.umte.di.repositories

import cz.uhk.umte.data.remote.service.GenshinDevService


class GenshinDevRepository(
    private val api: GenshinDevService
) {
    suspend fun fetchAllCharacters() =
        api.fetchAllCharacters()

    suspend fun fetchCharacterInfo(charName: String) =
        api.fetchCharacter(charName)
}