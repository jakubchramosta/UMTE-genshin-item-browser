package cz.uhk.umte.ui.async.weapons

import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeaponsViewModel(
    private val repo: GenshinDevRepository
) : BaseViewModel(){

    private val _weaponList = MutableStateFlow<List<WeaponInfoResponse>>(emptyList())
    val weaponList = _weaponList.asStateFlow()

    private val _textFieldInput = MutableStateFlow<String>("")
    val textFieldInput = _textFieldInput.asStateFlow()

    var compareList: MutableList<WeaponInfoResponse> = mutableListOf()

    init {
        fetchAllWeapons()
    }

    private fun fetchAllWeapons() {
        launch {
            val weaponsIDs = repo.fetchWeaponsIDs()
            try {
                repo.fetchAllWeapons().let {
                    for ((count, wepInfo) in it.withIndex()){
                        println(count)
                        println(wepInfo)
                        println(weaponsIDs[count])
                        wepInfo.weaponId = weaponsIDs[count]
                    }
                    _weaponList.emit(it)
                }

            } catch (e: Exception) {
                println(e)
                println(e.message)
            }
        }
    }

    fun updateTextField(input: String){
        _textFieldInput.update { input }
    }

    fun filterWepListByName(textInput: String) {
        var tempList = weaponList.value.filter { it.name.contains(textInput) }
        launch { _weaponList.emit(tempList) }
    }

    fun filterWeaponsByType(weaponType: String) {
        var tempList = weaponList.value.filter { it.type == weaponType }
        launch { _weaponList.emit(tempList) }
    }

    fun addWeaponToCompare(weapon: WeaponInfoResponse) {
        if (compareList.count() < 2) {
            compareList.add(weapon)
        }
    }

    fun removeWeapon(weapon: WeaponInfoResponse) {
        compareList.remove(weapon)
    }

    fun resetFilter() {
        fetchAllWeapons()
        launch { _textFieldInput.emit("") }
    }
}