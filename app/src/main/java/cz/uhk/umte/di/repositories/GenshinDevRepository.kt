package cz.uhk.umte.di.repositories

import cz.uhk.umte.data.remote.service.GenshinDevService


class GenshinDevRepository(
    private val api: GenshinDevService
) {
    suspend fun fetchCharacter(charName: String) =
        api.fetchCharacter(charName)

    suspend fun fetchAllCharacter() =
        api.fetchAllCharacter()
}