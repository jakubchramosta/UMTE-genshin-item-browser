package cz.uhk.umte.ui

import androidx.compose.runtime.Composable
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.uhk.umte.ui.async.characters.CharactersScreen
import cz.uhk.umte.ui.async.launches.RocketLaunchesScreen
import cz.uhk.umte.ui.async.rocket.RocketDetailScreen
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
            route = DestinationLaunches,
        ) {
            RocketLaunchesScreen(
                onNavigateDetail = { rocketIdButNotAtAll ->
                    controller.navigateRocketDetail(rocketIdButNotAtAll)
                },
            )
        }

        composable(
            route = DestinationRocketDetail,
            arguments = listOf(navArgument(ArgRocketId) { type = NavType.StringType })
        ) { navBackStackEntry ->
            RocketDetailScreen(
                rocketId = navBackStackEntry.arguments?.getString(ArgRocketId).orEmpty(),
            )
        }

        composable(
            route = DestinationCharacters,
        ) {
           CharactersScreen {
               onNavigateDetail = { characterName ->
                   controller.navigateCharacterInfo(characterName)
               }
           }
        }
    }
}

private const val ArgRocketId = "argRocketId"

private const val DestinationHome = "home"
private const val DestinationLaunches = "launches"
private const val DestinationRocketDetail = "rocket/{$ArgRocketId}"
private const val DestinationRoom = "room"

private const val ArgCharacterName = "argCharacterName"

private const val DestinationCharacters = "characters"
private const val DestinationCharacterInfo = "{$ArgCharacterName}"

fun NavHostController.navigateRocketDetail(rocketId: String) =
    navigate(DestinationRocketDetail.replaceArg(ArgRocketId, rocketId))

fun NavHostController.navigateRocketLaunches() =
    navigate(DestinationLaunches)

fun NavHostController.navigateRoomScreen() =
    navigate(DestinationRoom)


fun NavHostController.navigateCharacterScreen() =
    navigate(DestinationCharacters)

fun NavHostController.navigateCharacterInfo(characterName: String) =
    navigate(DestinationCharacterInfo.replaceArg(ArgCharacterName, characterName))

private fun String.replaceArg(argName: String, value: String) =
    replace("{$argName}", value)
