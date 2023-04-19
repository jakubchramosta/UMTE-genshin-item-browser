package cz.uhk.umte.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import cz.uhk.umte.R
import cz.uhk.umte.ui.navigateCharacterScreen

@Composable
fun HomeScreen(
    parentController: NavHostController,
) {
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
                parentController.navigateCharacterScreen()
            }
        ) {
            Text(text = stringResource(id = R.string.home_btn_artifacts))
        }
        Button(
            onClick = {
                parentController.navigateCharacterScreen()
            }
        ) {
            Text(text = stringResource(id = R.string.home_btn_weapons))
        }
    }
}