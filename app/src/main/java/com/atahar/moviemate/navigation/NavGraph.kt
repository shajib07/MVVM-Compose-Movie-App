package com.atahar.moviemate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.atahar.moviemate.R
import com.atahar.moviemate.data.model.moviedetail.Genre
import com.atahar.moviemate.ui.screens.movies.moviedetail.MovieDetail
import com.atahar.moviemate.ui.screens.movies.nowplaying.NowPlayingMovie
import com.atahar.moviemate.ui.screens.movies.popular.PopularMovie
import com.atahar.moviemate.ui.screens.movies.toprated.TopRatedMovie
import com.atahar.moviemate.ui.screens.movies.upcoming.UpcomingMovie
import com.atahar.moviemate.ui.screens.tvseries.airingtoday.AiringTodayTvSeries
import com.atahar.moviemate.ui.screens.tvseries.ontheair.OnTheAirTvSeries
import com.atahar.moviemate.ui.screens.tvseries.popular.PopularTvSeries
import com.atahar.moviemate.ui.screens.tvseries.toprated.TopRatedTvSeries

@Composable
fun Navigation(
    navController: NavHostController,
    genres: ArrayList<Genre>? = null
) {
    NavHost(navController = navController, startDestination = Screen.NowPlayingMovie.route) {
        composable(Screen.NowPlayingMovie.route) {
            NowPlayingMovie(
                navController = navController,
                genres
            )
        }
        composable(Screen.Popular.route) {
            PopularMovie(
                navController = navController,
                genres
            )
        }
        composable(Screen.TopRated.route) {
            TopRatedMovie(navController, genres)
        }
        composable(Screen.Upcoming.route) { UpcomingMovie(navController, genres) }

        composable(
            route = Screen.MovieDetail.route.plus(Screen.MovieDetail.objectPath),
            arguments = listOf(navArgument(Screen.MovieDetail.objectName) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.movie_detail)
            val movieId = it.arguments?.getInt(Screen.MovieDetail.objectName)
            movieId?.let {
                MovieDetail(
                    navController = navController, movieId
                )
            }
        }

        composable(Screen.AiringTodayTvSeries.route) {
            AiringTodayTvSeries(
                navController = navController,
                genres
            )
        }
        composable(Screen.OnTheAirTvSeries.route) {
            OnTheAirTvSeries(
                navController = navController,
                genres
            )
        }
        composable(Screen.PopularTvSeries.route) {
            PopularTvSeries(
                navController = navController,
                genres
            )
        }
        composable(Screen.TopRatedTvSeries.route) {
            TopRatedTvSeries(
                navController = navController,
                genres
            )
        }
        /*

                composable(
                    Screen.ArtistDetail.route.plus(Screen.ArtistDetail.objectPath),
                    arguments = listOf(navArgument(Screen.ArtistDetail.objectName) {
                        type = NavType.IntType
                    })
                ) {
                    label = stringResource(R.string.artist_detail)
                    val artistId = it.arguments?.getInt(Screen.ArtistDetail.objectName)
                    artistId?.let {
                        ArtistDetail(
                            artistId
                        )
                    }
                }

                composable(
                    Screen.TvSeriesDetail.route.plus(Screen.TvSeriesDetail.objectPath),
                    arguments = listOf(navArgument(Screen.TvSeriesDetail.objectName) {
                        type = NavType.IntType
                    })
                ) {
                    label = stringResource(R.string.tv_series_detail)
                    val movieId = it.arguments?.getInt(Screen.TvSeriesDetail.objectName)
                    movieId?.let {
                        TvSeriesDetail(
                            navController = navController, movieId
                        )
                    }
                }
                composable(Screen.FavoriteMovie.route) {
                    FavoriteMovie(navController)
                }
                composable(Screen.FavoriteTvSeries.route) {
                    FavoriteTvSeries(navController)
                }
            }*/
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}

