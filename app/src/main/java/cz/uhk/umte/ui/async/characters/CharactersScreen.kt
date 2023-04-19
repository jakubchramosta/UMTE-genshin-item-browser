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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.uhk.umte.R
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
        characterNameList = characterList.value,
        state = state.value,
        onNavigateInfo = onNavigateInfo,
    )
}

@Composable
fun CharactersViews(
    characterNameList: List<String> = emptyList(),
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
                    items(characterNameList) { character ->
                        Card {
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        onNavigateInfo(character)
                                    }
                                    .background(MaterialTheme.colors.error)
                                    .padding(16.dp)
                                    .background(MaterialTheme.colors.surface)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = character ?: "-",
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.weight(1F),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}