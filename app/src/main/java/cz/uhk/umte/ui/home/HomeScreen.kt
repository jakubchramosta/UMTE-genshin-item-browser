package cz.uhk.umte.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cz.uhk.umte.R
import cz.uhk.umte.ui.navigateCharacterScreen
import cz.uhk.umte.ui.navigateWeaponScreen

@Composable
fun HomeScreen(
    parentController: NavHostController,
) {
    Image(
        modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),

        painter = painterResource(R.drawable.genshin_impact_logo),
        contentDescription = null)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                parentController.navigateCharacterScreen()
            }
        ) {
            Text(text = stringResource(id = R.string.home_btn_characters))
        }
        Button(
            onClick = {
                parentController.navigateWeaponScreen()
            }
        ) {
            Text(text = stringResource(id = R.string.home_btn_weapons))
        }
    }
}