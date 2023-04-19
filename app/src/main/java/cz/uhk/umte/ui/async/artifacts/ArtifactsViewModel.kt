package cz.uhk.umte.ui.async.artifacts

import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArtifactsViewModel (
    private val repo: GenshinDevRepository
) : BaseViewModel(){

    private val _artifactIdList = MutableStateFlow<List<String>>(emptyList())
    val artifactIdList = _artifactIdList.asStateFlow()

    private val _artifactsList = MutableStateFlow<List<String>>(emptyList())

    init {
        fetchAllArtifacts()
    }

    private fun fetchAllArtifacts() {
        launch {
            val artifactsIdList = repo.fetchAllArtifacts()
            _artifactIdList.emit(artifactsIdList)
        }
    }
}