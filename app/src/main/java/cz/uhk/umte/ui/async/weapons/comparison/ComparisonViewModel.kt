package cz.uhk.umte.ui.async.weapons.comparison

import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ComparisonViewModel (
    private val repo: GenshinDevRepository
    ) : BaseViewModel() {

    private val _compareList = MutableStateFlow<List<WeaponInfoResponse>>(emptyList())
    val compareList = _compareList.value


}