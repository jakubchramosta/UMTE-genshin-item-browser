package cz.uhk.umte.ui.async.rocket

import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RocketDetailViewModel(
    private val rocketId: String,
    private val repo: GenshinDevRepository,
) : BaseViewModel() {

    private val _rocketDetail = MutableStateFlow<CharacterInfoResponse?>(null)
    val rocketDetail = _rocketDetail.asStateFlow()

    init {
        fetchRocketDetail()
    }

    private fun fetchRocketDetail() {
        launch {
            repo.fetchCharacter(rocketId)
                .let { detail ->
                    _rocketDetail.emit(detail)
                }
        }
    }
}