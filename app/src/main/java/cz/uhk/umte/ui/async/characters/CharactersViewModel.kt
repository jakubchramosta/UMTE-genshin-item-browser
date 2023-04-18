package cz.uhk.umte.ui.async.characters

import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharactersViewModel(
    private val repo: GenshinDevRepository
) : BaseViewModel(){

    private val _characterList = MutableStateFlow<List<String>>(emptyList())
    val characterList = _characterList.asStateFlow()

    init {
        fetchAllCharacters()
    }

    private fun fetchAllCharacters() {
        launch {
            val characters = repo.fetchAllCharacters()
            _characterList.emit(characters)
        }
    }
}