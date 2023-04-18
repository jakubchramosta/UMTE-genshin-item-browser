package cz.uhk.umte.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cz.uhk.umte.data.remote.ApiConfig
import cz.uhk.umte.data.remote.service.GenshinDevService
import cz.uhk.umte.di.repositories.GenshinDevRepository
import cz.uhk.umte.ui.async.launches.LaunchesViewModel
import cz.uhk.umte.ui.async.rocket.RocketDetailViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
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
    viewModel { LaunchesViewModel(get()) }
    viewModel { (rocketId: String) -> RocketDetailViewModel(rocketId, get()) }
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
            MediaType.get("application/json")
        )
    )
}.build()

private fun createClient() = OkHttpClient.Builder().build()