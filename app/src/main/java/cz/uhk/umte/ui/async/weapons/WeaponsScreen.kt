package cz.uhk.umte.ui.async.weapons

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
import androidx.compose.runtime.mutableStateOf
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
import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.ui.base.State
import org.koin.androidx.compose.getViewModel

@Composable
fun WeaponsScreen(
    viewModel: WeaponsViewModel = getViewModel(),
    ) {

    val weaponList = viewModel.weaponList.collectAsState()
    val state = viewModel.state.collectAsState()

    WeaponsViews(
        weaponList = weaponList.value,
        state = state.value,
    )

}

@Composable
fun WeaponsViews(
    weaponList: List<WeaponInfoResponse> = emptyList(),
    state: State = State.None,
    viewModel: WeaponsViewModel = getViewModel(),
) {
    var wpTypeMenuExp by remember { mutableStateOf(false) }

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
                        modifier = Modifier.padding(5.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ){
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Button(
                                onClick = {
                                    wpTypeMenuExp = !wpTypeMenuExp
                                }
                            ) {
                                Text(text = stringResource(id = R.string.home_btn_filterByWeaponType))
                            }

                            DropdownMenu(
                                expanded = wpTypeMenuExp,
                                onDismissRequest = { wpTypeMenuExp = false}
                            ) {
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterWeaponsByType("Sword")
                                        wpTypeMenuExp = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_sword))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterWeaponsByType("Claymore")
                                        wpTypeMenuExp = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_claymore))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterWeaponsByType("Catalyst")
                                        wpTypeMenuExp = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_catalyst))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterWeaponsByType("Bow")
                                        wpTypeMenuExp = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_bow))
                                }
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.filterWeaponsByType("Polearm")
                                        wpTypeMenuExp = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.weapon_type_polearm))
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
                        items(weaponList) { weapon ->
                            Card {
                                Row(
                                    modifier = Modifier.height(120.dp).padding(16.dp)
                                ) {
                                    AsyncImage(
                                        model = "https://api.genshin.dev/weapons/${weapon.weaponId}/icon",
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxWidth(0.3f).padding(5.dp),
                                        contentScale = ContentScale.FillWidth,
                                        alignment = Alignment.Center,
                                    )
                                    Text(
                                        text = weapon.name,
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