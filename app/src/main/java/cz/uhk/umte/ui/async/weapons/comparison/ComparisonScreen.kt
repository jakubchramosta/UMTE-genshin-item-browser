package cz.uhk.umte.ui.async.weapons.comparison

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.uhk.umte.data.remote.response.WeaponInfoResponse
import cz.uhk.umte.ui.async.weapons.WeaponsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ComparisonScreen(
    viewModel: WeaponsViewModel = getViewModel(),
) {

    //TODO: vytvořit list obsahující max 2 zbraně (pro porovnání)
    val compareList = viewModel.weaponList.collectAsState()

    ComparionView(
        compareList = compareList.value,
    )

}

@Composable
fun ComparionView(
    compareList: List<WeaponInfoResponse> = emptyList(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "test",
            style = MaterialTheme.typography.h4
        )
    }
}