package cz.uhk.umte.ui.async.weapons

import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WeaponsViewModel(
    private val repo: GenshinDevRepository
) : BaseViewModel(){

    private val _weaponList = MutableStateFlow<List<WeaponInfoResponse>>(emptyList())
    val weaponList = _weaponList.asStateFlow()

    init {
        fetchAllWeapons()
    }

    private fun fetchAllWeapons() {
        launch {
            println("started launch_____________")

            val weaponsIDs = repo.fetchWeaponsIDs()

            println(weaponsIDs)

            try {

                println("after weaponids---------")

                repo.fetchAllWeapons().let {
                    for ((count, wepInfo) in it.withIndex()){
                        println(count)
                        println(wepInfo)
                        println(weaponsIDs[count])
                        wepInfo.weaponId = weaponsIDs[count]
                    }

                    println("end of for ///////////////")

                    _weaponList.emit(it)
                }

            } catch (e: Exception) {
                println(e)
                println(e.message)
            }

            println("end of launch+++++++++++++")
        }
    }

    fun filterWeaponsByType(weaponType: String) {
        var tempList = weaponList.value.filter { it.type == weaponType }
        launch { _weaponList.emit(tempList) }
    }

    fun resetFilter() {
        fetchAllWeapons()
    }
}