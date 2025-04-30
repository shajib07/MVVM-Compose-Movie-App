package com.atahar.moviemate.ui.screens.mainscreen

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.atahar.moviemate.navigation.Screen
import com.atahar.moviemate.navigation.currentRoute

@Composable
fun BottomNavigationBar(
    navController: NavController,
    pagerState: PagerState,
) {
    NavigationBar {
        val items = if (pagerState.currentPage == 0) {
            listOf(
                Screen.NowPlayingNav,
                Screen.PopularNav,
                Screen.TopRatedNav,
                Screen.UpcomingNav,
            )
        } else {
            listOf(
                Screen.AiringTodayTvSeriesNav,
                Screen.OnTheAirTvSeriesNav,
                Screen.PopularTvSeriesNav,
                Screen.TopRatedTvSeriesNav,
            )
        }
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = item.navIcon,
                label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute(navController) == item.route,
                onClick = {
                    navController.singleTopNavigator(item.route)
                })
        }
    }
}

fun NavController.singleTopNavigator(route: String) {
    this.navigate(route) {
        graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}