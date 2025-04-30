package com.atahar.moviemate.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.atahar.moviemate.data.model.moviedetail.Genre
import com.atahar.moviemate.navigation.Navigation
import com.atahar.moviemate.navigation.Screen
import com.atahar.moviemate.navigation.currentRoute
import com.atahar.moviemate.ui.screens.mainscreen.tabview.FavoriteTabView
import com.atahar.moviemate.ui.screens.mainscreen.tabview.MovieTvSeriesTabView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navHostController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val genreList = mainViewModel.genreList.collectAsState()

    val pagerState = rememberPagerState { 2 }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Movie Mate",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                scrollBehavior = scrollBehavior,
                actions = { },
            )
        },
        bottomBar = {
            when (currentRoute(navHostController)) {
                Screen.NowPlayingMovie.route,
                Screen.Popular.route,
                Screen.TopRated.route,
                Screen.Upcoming.route,
                Screen.AiringTodayTvSeries.route,
                Screen.OnTheAirTvSeriesNav.route,
                Screen.PopularTvSeries.route,
                Screen.TopRatedTvSeries.route -> {
                    BottomNavigationBar(navHostController, pagerState)
                }
            }
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            MainView(
                navHostController = navHostController,
                pagerState,
                genres = genreList.value as ArrayList<Genre>?,
                false,
            )
        }
    }

}

@Composable
fun MainView(
    navHostController: NavHostController,
    pagerState: PagerState,
    genres: ArrayList<Genre>? = null,
    isFavorite: Boolean,
) {
    Column {
        if (currentRoute(navHostController) !in listOf(
                Screen.MovieDetail.route,
                Screen.TvSeriesDetail.route,
                Screen.ArtistDetail.route
            )
        ) {
            if (isFavorite) {
                FavoriteTabView(navHostController)
            } else {
                MovieTvSeriesTabView(navHostController, pagerState)
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) {
            Navigation(navHostController, genres)
        }
    }

}