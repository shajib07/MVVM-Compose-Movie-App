package com.atahar.moviemate.data.repository.remote.movie

import androidx.paging.PagingData
import com.atahar.moviemate.data.model.Genres
import com.atahar.moviemate.data.model.MovieItem
import com.atahar.moviemate.data.model.SearchBaseModel
import com.atahar.moviemate.data.model.artist.Artist
import com.atahar.moviemate.data.model.moviedetail.MovieDetail
import com.atahar.moviemate.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {

    fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>>
    fun recommendedMovie(movieId: Int): Flow<DataState<List<MovieItem>>>
    fun movieSearch(searchKey: String): Flow<DataState<SearchBaseModel>>
    fun genreList(): Flow<DataState<Genres>>
    fun movieCredit(movieId: Int): Flow<DataState<Artist>>
    fun nowPlayingMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun popularMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun topRatedMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>>
    fun upcomingMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>>


}