package cz.uhk.umte.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cz.uhk.umte.data.remote.ApiConfig
import cz.uhk.umte.data.remote.service.GenshinDevService
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.async.characters.characterInfo.CharacterInfoViewModel
import cz.uhk.umte.ui.async.characters.CharactersViewModel
import cz.uhk.umte.ui.async.weapons.WeaponsViewModel
import cz.uhk.umte.ui.async.weapons.comparison.ComparisonViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules by lazy { listOf(dataModule, uiModule) }

val dataModule = module {
    repositories()
    apiServices()
}

val uiModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { WeaponsViewModel(get())}
    viewModel { (wepID1: String, wepID2: String) -> ComparisonViewModel(wepID1, wepID2, get())}
    viewModel { (characterName: String) -> CharacterInfoViewModel(characterName, get()) }
}

private fun Module.repositories() {
    single { GenshinDevRepository(get()) }
}

private fun Module.apiServices() {
    single { createRetrofit(createClient()) }
    single { get<Retrofit>().create(GenshinDevService::class.java) }
}

private val json = Json {
    ignoreUnknownKeys = true
}

@OptIn(ExperimentalSerializationApi::class)
private fun createRetrofit(
    client: OkHttpClient,
    baseUrl: String = ApiConfig.BaseApiUrl,
) = Retrofit.Builder().apply {
    client(client)
    baseUrl(baseUrl)
    addConverterFactory(
        json.asConverterFactory(
            "application/json".toMediaType()
        )
    )
}.build()

private fun createClient() = OkHttpClient.Builder().build()