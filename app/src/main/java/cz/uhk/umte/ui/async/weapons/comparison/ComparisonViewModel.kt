package cz.uhk.umte.ui.async.weapons.comparison

import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.PrimitiveIterator

class ComparisonViewModel (
    private val wepID1: String,
    private val wepID2: String,
    private val repo: GenshinDevRepository,
    ) : BaseViewModel() {

    private val _weapon1 = MutableStateFlow<WeaponInfoResponse?>(null)
    val weapon1 = _weapon1.asStateFlow()

    private val _weapon2 = MutableStateFlow<WeaponInfoResponse?>(null)
    val weapon2 = _weapon2.asStateFlow()

    init {
        fetchWeaponsByID()
    }

    fun fetchWeaponsByID() {
        launch {
            repo.fetchWeaponByName(wepID1)
                .let { info ->
                    if (info != null) {
                        info.weaponId = wepID1
                    }
                    _weapon1.emit(info)
                }

            repo.fetchWeaponByName(wepID2)
                .let { info ->
                    if (info != null) {
                        info.weaponId = wepID2
                    }
                    _weapon2.emit(info)
                }
        }
    }
}