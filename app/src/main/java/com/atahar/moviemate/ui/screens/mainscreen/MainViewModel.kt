package com.atahar.moviemate.ui.screens.mainscreen

import android.provider.ContactsContract.Data
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atahar.moviemate.data.model.Genres
import com.atahar.moviemate.data.model.moviedetail.Genre
import com.atahar.moviemate.data.repository.remote.movie.MovieRepository
import com.atahar.moviemate.utils.AppConstant
import com.atahar.moviemate.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _genreList: MutableStateFlow<MutableList<Genre>> = MutableStateFlow(mutableListOf())
    val genreList: StateFlow<MutableList<Genre>> = _genreList.asStateFlow()


    init {
        genreList()
    }

    private fun genreList() {
        viewModelScope.launch {
            movieRepository.genreList().collect { dataState ->
                when (dataState) {
                    DataState.Loading -> {}
                    is DataState.Success -> {
                        _genreList.value = dataState.data.genres.toMutableList()
                        if (_genreList.value.first().name != AppConstant.DEFAULT_GENRE_ITEM) {
                            _genreList.value.add(0, Genre(null, AppConstant.DEFAULT_GENRE_ITEM))
                        }
                    }

                    is DataState.Error -> {
                        _genreList.value.add(0, Genre(null, AppConstant.DEFAULT_GENRE_ITEM))
                    }
                }

            }
        }
    }
}