package com.atahar.moviemate.ui.screens.movies.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.atahar.moviemate.data.model.moviedetail.Genre
import com.atahar.moviemate.ui.component.MovieItems

@Composable
fun PopularMovie(
    navController: NavController,
    genres: ArrayList<Genre>? = null,
) {
    val viewModel = hiltViewModel<PopularMovieViewModel>()
    val movieItems = viewModel.getMovies().collectAsLazyPagingItems()
    val selectedGenre: State<Genre> = viewModel.selectedGenre.collectAsState()

    MovieItems(navController, movieItems, genres, selectedGenre.value) { genre ->
        viewModel.updateSelectedGenre(genre)
    }
}
