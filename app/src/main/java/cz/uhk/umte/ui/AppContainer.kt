package cz.uhk.umte.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                onNavigateDetail = { rocketId ->
                    controller.navigateRocketDetail(rocketId)
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
    }
}

private const val ArgRocketId = "argRocketId"

private const val DestinationHome = "home"
private const val DestinationLaunches = "launches"
private const val DestinationRocketDetail = "rocket/{$ArgRocketId}"
private const val DestinationRoom = "room"

fun NavHostController.navigateRocketDetail(rocketId: String) =
    navigate(DestinationRocketDetail.replaceArg(ArgRocketId, rocketId))

fun NavHostController.navigateRocketLaunches() =
    navigate(DestinationLaunches)

fun NavHostController.navigateRoomScreen() =
    navigate(DestinationRoom)

private fun String.replaceArg(argName: String, value: String) =
    replace("{$argName}", value)