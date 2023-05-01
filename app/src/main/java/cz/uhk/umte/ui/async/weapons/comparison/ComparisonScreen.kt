package cz.uhk.umte.ui.async.weapons.comparison

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.ui.async.weapons.WeaponsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ComparisonScreen(
    viewModel: ComparisonViewModel = getViewModel(),
//    compareList: List<WeaponInfoResponse>
) {
    val compareList = viewModel.compareList

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        if (compareList.isNotEmpty()) {
            for (wep in compareList) {
                WeaponDetail(wep)
            }
        } else {
            Text(
                text = "Select two weapons."
            )
        }
    }
}

@Composable
fun WeaponDetail(
    wep: WeaponInfoResponse
){
    Column {
        AsyncImage(
            model = "https://api.genshin.dev/weapons/${wep.weaponId}/icon",
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.3f).padding(5.dp),
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
        Text(text = "Passive: " + wep.passiveDesc)
    }
}