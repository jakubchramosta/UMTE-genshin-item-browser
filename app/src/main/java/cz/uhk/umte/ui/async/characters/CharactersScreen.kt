package cz.uhk.umte.ui.async.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.uhk.umte.R
import cz.uhk.umte.data.remote.response.CharacterInfoResponse
import cz.uhk.umte.ui.base.State
import org.koin.androidx.compose.getViewModel


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = getViewModel(),
    onNavigateInfo: (String) -> Unit,
) {

    val characterList = viewModel.characterList.collectAsState()
    val state = viewModel.state.collectAsState()

    CharactersViews(
        characterList = characterList.value,
        state = state.value,
        onNavigateInfo = onNavigateInfo,
    )
}

@Preview
@Composable
fun CharactersViews(
    characterList: List<CharacterInfoResponse> = emptyList(),
    state: State = State.None,
    onNavigateInfo: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            State.None, State.Loading -> {
                CircularProgressIndicator()
            }
            is State.Failure -> {
                Button(onClick = { state.repeat() }) {
                    Text(text = stringResource(R.string.retry))
                }
            }
            is State.Success -> {
                // List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(all = 8.dp),
                ) {
                    items(characterList) { character ->
                        Card {
                            Row(
                                modifier = Modifier.height(120.dp)
                                    .clickable {
                                        onNavigateInfo(character.characterId)
                                    }
                                    .background(MaterialTheme.colors.primaryVariant)
                                    .padding(16.dp)
                            ) {
                                AsyncImage(
                                    model = "https://api.genshin.dev/characters/${character.characterId}/icon-big",
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxWidth(0.3f).padding(5.dp),
                                    contentScale = ContentScale.FillWidth,
                                    alignment = Alignment.Center,
                                )
                                Text(
                                    text = character.name,
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.weight(1F).padding(8.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}