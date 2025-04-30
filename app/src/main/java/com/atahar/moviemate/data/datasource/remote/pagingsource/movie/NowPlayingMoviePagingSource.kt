package com.atahar.moviemate.data.datasource.remote.pagingsource.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atahar.moviemate.data.datasource.remote.ApiService
import com.atahar.moviemate.data.model.MovieItem
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class NowPlayingMoviePagingSource @Inject constructor(
    private val apiService: ApiService,
    private val genreId: String?,
) : PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPage = params.key ?: 1
            val movieList = apiService.nowPlayingMovies(nextPage, genreId)
            LoadResult.Page(
                data = movieList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movieList.results.isNotEmpty()) movieList.page + 1 else null
            )
        } catch (exception: IOException) {
            Timber.e("Exception ${exception.message}")
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            Timber.e("httpException ${httpException.message}")
            return LoadResult.Error(httpException)
        }
    }
}
