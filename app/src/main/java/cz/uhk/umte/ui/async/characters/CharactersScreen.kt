package cz.uhk.umte.ui.async.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    val textFieldInput = viewModel.textFieldInput.collectAsState()

    CharactersViews(
        characterList = characterList.value,
        state = state.value,
        textFieldInput = textFieldInput.value,
        onNavigateInfo = onNavigateInfo,
        )
}

@Composable
fun CharactersViews(
    characterList: List<CharacterInfoResponse> = emptyList(),
    state: State = State.None,
    textFieldInput: String = "",
    onNavigateInfo: (String) -> Unit = {},
    viewModel: CharactersViewModel = getViewModel(),
) {
    var weaponMenuExpanded by remember { mutableStateOf(false) }
    var elementMenuExpanded by remember { mutableStateOf(false) }

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
                Column {
                    Row (
                        modifier = Modifier.padding(5.dp),
                    ){
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = textFieldInput,
                            onValueChange = { viewModel.updateTextField(it) },
                            label = {
                                Text(text = "Filter by name")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Search,
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    viewModel.filterCharListByName(textFieldInput)
                                })
                        )
                    }
                    Row (
                        modifier = Modifier.padding(5.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ){
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Button(
                                onClick = {
                                    weaponMenuExpanded = !weaponMenuExpanded
                                }
                            ) {
                                Text(text = stringResource(id = R.string.home_btn_filterByWeapon))
                            }

                            DropdownMenu(
                                expanded = weaponMenuExpanded,
                                onDismissRequest = { weaponMenuExpanded = false}
                            ) {
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByWeapon("Sword")
                                        weaponMenuExpanded = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_sword))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByWeapon("Claymore")
                                        weaponMenuExpanded = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_claymore))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByWeapon("Catalyst")
                                        weaponMenuExpanded = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_catalyst))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByWeapon("Bow")
                                        weaponMenuExpanded = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_bow))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByWeapon("Polearm")
                                        weaponMenuExpanded = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_polearm))
                                }
                            }
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                            Button(
                                onClick = {
                                    elementMenuExpanded = !elementMenuExpanded
                                }
                            ) {
                                Text(text = stringResource(id = R.string.home_btn_filterByElement))
                            }

                            DropdownMenu(
                                expanded = elementMenuExpanded,
                                onDismissRequest = { elementMenuExpanded = false}
                            ) {
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByElement("Pyro")
                                        elementMenuExpanded = false
                                    }
                                ) {
                                    Text("Pyro")
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByElement("Anemo")
                                        elementMenuExpanded = false
                                    }
                                ) {
                                    Text("Anemo")
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByElement("Electro")
                                        elementMenuExpanded = false
                                    }
                                ) {
                                    Text("Electro")
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByElement("Cryo")
                                        elementMenuExpanded = false
                                    }
                                ) {
                                    Text("Cryo")
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByElement("Geo")
                                        elementMenuExpanded = false
                                    }
                                ) {
                                    Text("Geo")
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterCharListByElement("Hydro")
                                        elementMenuExpanded = false
                                    }
                                ) {
                                    Text("Hydro")
                                }
                            }
                        }
                        Column (
                            horizontalAlignment = Alignment.End,
                                ) {
                            Button(
                                onClick = {
                                    viewModel.resetFilter()
                                }
                            ) {
                                Text(text = stringResource(id = R.string.home_btn_resetFilter))
                            }
                        }
                    }

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
}