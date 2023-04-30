package cz.uhk.umte.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.uhk.umte.ui.async.characters.characterInfo.CharacterInfoScreen
import cz.uhk.umte.ui.async.characters.CharactersScreen
import cz.uhk.umte.ui.async.weapons.WeaponsScreen
import cz.uhk.umte.ui.async.weapons.comparison.ComparisonScreen
import cz.uhk.umte.ui.home.HomeScreen

@Composable
fun AppContainer(
    controller: NavHostController,
) {

    NavHost(
        navController = controller,
        startDestination = DestinationHome,
    ) {
        // Graph navigation

        composable(
            route = DestinationHome,
        ) {
            HomeScreen(
                parentController = controller,
            )
        }

        composable(
            route = DestinationCharacters,
        ) {
            CharactersScreen (
                onNavigateInfo = { characterName ->
                    controller.navigateCharacterInfo(characterName)
                },
            )
        }

        composable(
            route = DestinationCharacterInfo,
            arguments = listOf(navArgument(ArgCharacterName) { type = NavType.StringType })
        ) { navBackStackEntry ->
            CharacterInfoScreen(
                characterName = navBackStackEntry.arguments?.getString(ArgCharacterName).orEmpty(),
            )
        }

        composable(
            route = DestinationWeapons,
        ) {
            WeaponsScreen(
                toComparisonScreen = { controller.navigateToCompare() }
            )
        }

        composable(
            route = DestinationCompareScreen,
        ) {
            ComparisonScreen()
        }
    }
}

private const val ArgCharacterName = "argCharacterName"

private const val DestinationHome = "home"
private const val DestinationCharacters = "characters"
private const val DestinationCharacterInfo = "character/{$ArgCharacterName}"

private const val DestinationWeapons = "weapons"
private const val DestinationCompareScreen = "compare"

fun NavHostController.navigateCharacterScreen() =
    navigate(DestinationCharacters)

fun NavHostController.navigateCharacterInfo(characterName: String) =
    navigate(DestinationCharacterInfo.replaceArg(ArgCharacterName, characterName))

fun NavHostController.navigateWeaponScreen() =
    navigate(DestinationWeapons)

fun NavHostController.navigateToCompare() =
    navigate(DestinationCompareScreen)

private fun String.replaceArg(argName: String, value: String) =
    replace("{$argName}", value)
