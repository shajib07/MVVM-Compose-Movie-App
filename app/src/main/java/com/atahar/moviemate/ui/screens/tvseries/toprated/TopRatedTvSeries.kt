package com.atahar.moviemate.ui.screens.tvseries.toprated

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.atahar.moviemate.data.model.moviedetail.Genre
import com.atahar.moviemate.ui.component.TvSeriesItems

@Composable
fun TopRatedTvSeries(
    navController: NavController,
    genres: ArrayList<Genre>? = null
) {
    val viewModel = hiltViewModel<TopRatedTvSeriesViewModel>()
    val tvSeries = viewModel.getTvSeries().collectAsLazyPagingItems()
    val selectedGenre: State<Genre> = viewModel.selectedGenre.collectAsState()

    TvSeriesItems (navController, tvSeries, genres, selectedGenre.value) { genre ->
        viewModel.updateSelectedGenre(genre)
    }
}
