package cz.uhk.umte.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.uhk.umte.ui.async.characterInfo.CharacterInfoScreen
import cz.uhk.umte.ui.async.characters.CharactersScreen
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
    }
}

private const val ArgCharacterName = "argCharacterName"

private const val DestinationHome = "home"
private const val DestinationCharacters = "characters"
private const val DestinationCharacterInfo = "character/{$ArgCharacterName}"

fun NavHostController.navigateCharacterScreen() =
    navigate(DestinationCharacters)

fun NavHostController.navigateCharacterInfo(characterName: String) =
    navigate(DestinationCharacterInfo.replaceArg(ArgCharacterName, characterName))

private fun String.replaceArg(argName: String, value: String) =
    replace("{$argName}", value)
