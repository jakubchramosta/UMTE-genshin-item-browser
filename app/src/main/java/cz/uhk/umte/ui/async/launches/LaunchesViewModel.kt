package cz.uhk.umte.ui.async.launches

import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LaunchesViewModel(
    private val repo: GenshinDevRepository,
) : BaseViewModel() {

    private val _successfulRocketLaunches = MutableStateFlow<List<String>>(emptyList())
    val successRocketLaunches = _successfulRocketLaunches.asStateFlow()

    init {
        fetchSuccessfulRocketLaunches()
    }

    private fun fetchSuccessfulRocketLaunches() {
        launch {
            val launches = repo.fetchAllCharacter()
            _successfulRocketLaunches.emit(launches)
        }
    }
}