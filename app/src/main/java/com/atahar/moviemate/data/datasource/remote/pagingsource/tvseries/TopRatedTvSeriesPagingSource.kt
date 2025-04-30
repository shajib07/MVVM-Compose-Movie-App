package com.atahar.moviemate.data.datasource.remote.pagingsource.tvseries

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atahar.moviemate.data.datasource.remote.ApiService
import com.atahar.moviemate.data.model.TvSeriesItem
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class TopRatedTvSeriesPagingSource @Inject constructor(
    private val apiService: ApiService,
    private val genreId: String?,
) : PagingSource<Int, TvSeriesItem>() {
    override fun getRefreshKey(state: PagingState<Int, TvSeriesItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSeriesItem> {
        return try {
            val nextPage = params.key ?: 1
            val tvSeriesList = apiService.topRatedTvSeries(nextPage, genreId)
            LoadResult.Page(
                data = tvSeriesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (tvSeriesList.results.isNotEmpty()) tvSeriesList.page + 1 else null
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
