package cz.uhk.umte.ui.async.characterInfo

import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterInfoViewModel(
    private val characterName: String,
    private val repo: GenshinDevRepository,
) : BaseViewModel() {

    private val _characterInfo = MutableStateFlow<CharacterInfoResponse?>(null)
    val characterInfo = _characterInfo.asStateFlow()

    init {
        fetchCharacterInfo()
    }

    private fun fetchCharacterInfo() {
        launch {
            repo.fetchCharacterInfo(characterName)
                .let { info ->
                    _characterInfo.emit(info)
                }
        }
    }
}