package com.atahar.moviemate.data.repository.remote.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.atahar.moviemate.data.datasource.remote.ApiService
import com.atahar.moviemate.data.datasource.remote.pagingsource.movie.NowPlayingMoviePagingSource
import com.atahar.moviemate.data.datasource.remote.pagingsource.movie.PopularMoviePagingDataSource
import com.atahar.moviemate.data.datasource.remote.pagingsource.movie.TopRatedMoviePagingSource
import com.atahar.moviemate.data.datasource.remote.pagingsource.movie.UpcomingMoviePagingSource
import com.atahar.moviemate.data.model.Genres
import com.atahar.moviemate.data.model.MovieItem
import com.atahar.moviemate.data.model.SearchBaseModel
import com.atahar.moviemate.data.model.artist.Artist
import com.atahar.moviemate.data.model.moviedetail.MovieDetail
import com.atahar.moviemate.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) : MovieRepositoryInterface {
    override fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override fun recommendedMovie(movieId: Int): Flow<DataState<List<MovieItem>>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.recommendedMovie(movieId)
            emit(DataState.Success(searchResult.results))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override fun movieSearch(searchKey: String): Flow<DataState<SearchBaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.searchMovie(searchKey)
            emit(DataState.Success(searchResult))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override fun genreList(): Flow<DataState<Genres>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.genreList()
            emit(DataState.Success(genreResult))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override fun movieCredit(movieId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.movieCredit(movieId)
            emit(DataState.Success(artistResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }


    override fun nowPlayingMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { NowPlayingMoviePagingSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override fun popularMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { PopularMoviePagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override fun topRatedMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { TopRatedMoviePagingSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override fun upcomingMoviePagingSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { UpcomingMoviePagingSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow


}