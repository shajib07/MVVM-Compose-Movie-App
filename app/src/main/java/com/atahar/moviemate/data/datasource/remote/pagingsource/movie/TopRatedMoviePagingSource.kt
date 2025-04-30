package com.atahar.moviemate.data.datasource.remote.pagingsource.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atahar.moviemate.data.datasource.remote.ApiService
import com.atahar.moviemate.data.model.GenreId
import com.atahar.moviemate.data.model.MovieItem
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class TopRatedMoviePagingSource @Inject constructor(
    private val apiService: ApiService,
    private val genreId: String?
) : PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPage = params.key ?: 1
            val movieModel = apiService.topRatedMovies(nextPage, genreId)
            LoadResult.Page(
                data = movieModel.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movieModel.results.isNotEmpty()) movieModel.page + 1 else null
            )
        } catch (ioException: IOException) {
            Timber.e("exception ${ioException.message}")
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            Timber.e("httpException ${httpException.message}")
            LoadResult.Error(httpException)
        }
    }
}
