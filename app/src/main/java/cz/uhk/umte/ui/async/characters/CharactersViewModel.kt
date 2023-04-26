package cz.uhk.umte.ui.async.characters

import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharactersViewModel(
    private val repo: GenshinDevRepository
) : BaseViewModel(){

    private val _characterList = MutableStateFlow<List<CharacterInfoResponse>>(emptyList())
    val characterList = _characterList.asStateFlow()
    private val _characterIDs = MutableStateFlow<List<String>>(emptyList())

    init {
//        fetchAllCharacters()
//        fetchCharactersIDs()
//        setCharactersIDs()
        fetchAllCharacters2()
    }

    private fun fetchAllCharacters(){
        launch {
            val characters = repo.fetchAllCharacters()
            _characterList.emit(characters)
        }
    }

    private fun fetchCharactersIDs(){
        launch {
            val charactersIDs = repo.fetchCharactersIDs()
            _characterIDs.emit(charactersIDs)
        }
    }

    private fun setCharactersIDs(){
        val charactersIDsList = _characterIDs.asStateFlow()

        var count = 0
        for (charInfo in _characterList.value){
            charInfo.characterId = charactersIDsList.value[count]
            count++
        }
    }

    private fun fetchAllCharacters1() {
        launch {
            //TODO: v této části je potřeba aby se přidalo ID každému charakteru (oba listy jsou seřazeny abecedně)
            val characterList = repo.fetchAllCharacters()
            val charactersIDs = repo.fetchCharactersIDs()

            var count = 0
            for (charID in charactersIDs){
                characterList[count].characterId = charID
                count++
            }

            _characterList.emit(characterList)
        }
    }

    private fun fetchAllCharacters2(){
        launch {
            //TODO: pokus "vynutit" fetch všech charakterů
            val charactersIDs = repo.fetchCharactersIDs()

            repo.fetchAllCharacters().let {
                var count = 0
                for (charInfo in it){
                    println(charInfo)
                    println(charactersIDs[count])
                    charInfo.characterId = charactersIDs[count]
                    count++
                }
                _characterList.emit(it)
            }
        }
    }
}