package cz.uhk.umte.ui.async.characters

import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CharactersViewModel(
    private val repo: GenshinDevRepository
) : BaseViewModel(){

    private val _characterList = MutableStateFlow<List<CharacterInfoResponse>>(emptyList())
    val characterList = _characterList.asStateFlow()

    private val _textFieldInput = MutableStateFlow<String>("")
    val textFieldInput = _textFieldInput.asStateFlow()

    init {
        fetchAllCharacters()
    }

    private fun fetchAllCharacters(){
        launch {
            val charactersIDs = repo.fetchCharactersIDs()

            repo.fetchAllCharacters().let {
                for ((count, charInfo) in it.withIndex()){
                    charInfo.characterId = charactersIDs[count]
                }
                _characterList.emit(it)
            }
        }
    }

    fun updateTextField(input: String){
        _textFieldInput.update { input }
    }

    fun filterCharListByName(textInput: String) {
        var tempList = characterList.value.filter { it.name.contains(textInput) }
        launch { _characterList.emit(tempList) }
    }

    fun filterCharListByWeapon(weaponType: String) {
        var tempList = characterList.value.filter { it.weapon == weaponType }
        launch { _characterList.emit(tempList) }
    }

    fun filterCharListByElement(elementType: String) {
        var tempList = characterList.value.filter { it.vision == elementType }
        launch { _characterList.emit(tempList) }
    }

    fun resetFilter() {
        fetchAllCharacters()
        launch { _textFieldInput.emit("") }
    }
}
