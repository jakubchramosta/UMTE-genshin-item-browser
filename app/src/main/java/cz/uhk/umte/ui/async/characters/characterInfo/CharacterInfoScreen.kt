package cz.uhk.umte.ui.async.characters.characterInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.uhk.umte.R
import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import cz.uhk.umte.ui.base.State
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CharacterInfoScreen(
    characterName: String,
    viewModel: CharacterInfoViewModel = getViewModel {
        parametersOf(characterName)
    }
) {
    val state = viewModel.state.collectAsState()
    val characterInfo = viewModel.characterInfo.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (val result = state.value) {
            State.None, State.Loading -> {
                CircularProgressIndicator()
            }
            is State.Failure -> {
                Button(onClick = { result.repeat() }) {
                    Text(text= stringResource(R.string.retry))
                }
            }
            is State.Success -> {
                characterInfo.value?.let { characterInfo ->
                    CharacterInfoView(characterInfo, characterName)
                } ?: run {
                    Text(text = "No data available")
                }
            }
        }
    }
}

@Composable
fun CharacterInfoView(info: CharacterInfoResponse, character: String) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        AsyncImage(
            model = "https://api.genshin.dev/characters/$character/gacha-splash",
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = info.name,
            style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Vision: "+info.vision)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Weapon: "+info.weapon)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Nation: "+info.nation)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Affiliation: "+info.affiliation)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Description:")
        info.description?.let { Text(text = it) }
    }
}