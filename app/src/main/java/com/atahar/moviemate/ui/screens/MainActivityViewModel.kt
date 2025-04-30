package com.atahar.moviemate.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atahar.moviemate.data.repository.remote.movie.MovieRepository
import com.atahar.moviemate.data.repository.remote.movie.MovieRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val repo: MovieRepository
) : ViewModel(){

    init {
        viewModelScope.launch {
            repo.nowPlayingMoviePagingSource(null).collect { it ->
                Timber.d("here movieitem = ${it}")
            }
        }
    }
}