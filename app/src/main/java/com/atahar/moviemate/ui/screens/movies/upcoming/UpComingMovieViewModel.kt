package com.atahar.moviemate.ui.screens.movies.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.atahar.moviemate.data.model.MovieItem
import com.atahar.moviemate.data.model.moviedetail.Genre
import com.atahar.moviemate.data.repository.remote.movie.MovieRepository
import com.atahar.moviemate.utils.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class UpComingMovieViewModel @Inject constructor(
    val repo: MovieRepository
) : ViewModel() {
    private val _selectedGenre = MutableStateFlow(Genre(null, AppConstant.DEFAULT_GENRE_ITEM))
    val selectedGenre: StateFlow<Genre> = _selectedGenre.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMovies(): Flow<PagingData<MovieItem>> {
        return selectedGenre.flatMapLatest {
            repo.upcomingMoviePagingSource(it.id.toString())
        }.cachedIn(viewModelScope)
    }

    fun updateSelectedGenre(genre: Genre) {
        _selectedGenre.value = genre
    }
}