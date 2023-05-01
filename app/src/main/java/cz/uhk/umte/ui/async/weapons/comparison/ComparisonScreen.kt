package cz.uhk.umte.ui.async.weapons.comparison

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.ui.async.characters.characterInfo.CharacterInfoView
import cz.uhk.umte.ui.async.weapons.WeaponsViewModel
import cz.uhk.umte.ui.base.State
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ComparisonScreen(
    wepID1: String,
    wepID2: String,
    viewModel: ComparisonViewModel = getViewModel{
        parametersOf(wepID1, wepID2)
    },
) {
    val state = viewModel.state.collectAsState()
    val weapon1 = viewModel.weapon1.collectAsState()
    val weapon2 = viewModel.weapon2.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        when (val result = state.value) {
            State.None, State.Loading -> {
                CircularProgressIndicator()
            }

            is State.Failure -> {
                Button(onClick = { result.repeat() }) {
                    Text(text = stringResource(R.string.retry))
                }
            }

            is State.Success -> {
                Row (
                    modifier = Modifier.fillMaxSize()
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        weapon1.value?.let { weapon ->
                            WeaponDetail(weapon)
                        } ?: run {
                            Text(text = "No data available")
                        }
                    }
                    Spacer(
                        modifier = Modifier.width(5.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        weapon2.value?.let { weapon ->
                            WeaponDetail(weapon)
                        } ?: run {
                            Text(text = "No data available")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeaponDetail(
    wep: WeaponInfoResponse
) {
    AsyncImage(
        model = "https://api.genshin.dev/weapons/${wep.weaponId}/icon",
        contentDescription = null,
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.Center,
    )
    Text(
        text = wep.name,
        style = MaterialTheme.typography.h5
    )
    Text(text = "Type: " + wep.type)
    Text(text = "Base ATK: ")
    Text(text = "Sub stat: " + wep.subStat)
    Text(text = "Passive:")
    wep.passiveDesc?.let { Text(text = it) }
}