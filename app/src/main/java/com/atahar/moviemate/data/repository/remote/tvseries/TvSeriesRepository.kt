package com.atahar.moviemate.data.repository.remote.tvseries

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.atahar.moviemate.data.datasource.remote.ApiService
import com.atahar.moviemate.data.datasource.remote.pagingsource.tvseries.AiringTodayTvSeriesPagingSource
import com.atahar.moviemate.data.datasource.remote.pagingsource.tvseries.OnTheAirTvSeriesPagingSource
import com.atahar.moviemate.data.datasource.remote.pagingsource.tvseries.PopularTvSeriesPagingSource
import com.atahar.moviemate.data.datasource.remote.pagingsource.tvseries.TopRatedTvSeriesPagingSource
import com.atahar.moviemate.data.model.SearchBaseModel
import com.atahar.moviemate.data.model.TvSeriesItem
import com.atahar.moviemate.data.model.artist.ArtistDetail
import com.atahar.moviemate.data.model.tv_series_detail.TvSeriesDetail
import com.atahar.moviemate.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(
    private val apiService: ApiService
) : TvSeriesRepositoryInterface {
    override fun airingTodayTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { AiringTodayTvSeriesPagingSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override fun onTheAirTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { OnTheAirTvSeriesPagingSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override fun popularTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { PopularTvSeriesPagingSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override fun topRatedTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { TopRatedTvSeriesPagingSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        ).flow

    override fun searchTvSeries(searchKey: String): Flow<DataState<SearchBaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.searchTvSeries(searchKey)
            emit(DataState.Success(searchResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun tvSeriesDetail(seriesId: Int): Flow<DataState<TvSeriesDetail>> = flow {
        emit(DataState.Loading)
        try {
            val apiResponse = apiService.tvSeriesDetail(seriesId)
            emit(DataState.Success(apiResponse))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun recommendedTvSeries(seriesId: Int): Flow<DataState<List<TvSeriesItem>>> = flow {
        emit(DataState.Loading)
        try {
            val apiResponse = apiService.recommendedTvSeries(seriesId)
            emit(DataState.Success(apiResponse.results))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>> = flow {
        emit(DataState.Loading)
        try {
            val apiResponse = apiService.artistDetail(personId)
            emit(DataState.Success(apiResponse))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}