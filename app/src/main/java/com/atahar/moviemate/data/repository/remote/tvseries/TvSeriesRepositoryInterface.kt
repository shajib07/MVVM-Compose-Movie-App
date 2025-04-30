package com.atahar.moviemate.data.repository.remote.tvseries

import androidx.paging.PagingData
import com.atahar.moviemate.data.model.SearchBaseModel
import com.atahar.moviemate.data.model.TvSeriesItem
import com.atahar.moviemate.data.model.artist.ArtistDetail
import com.atahar.moviemate.data.model.tv_series_detail.TvSeriesDetail
import com.atahar.moviemate.utils.DataState
import kotlinx.coroutines.flow.Flow

interface TvSeriesRepositoryInterface {
    fun airingTodayTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    fun onTheAirTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    fun popularTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    fun topRatedTvSeriesPagingSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    fun searchTvSeries(searchKey: String): Flow<DataState<SearchBaseModel>>
    fun tvSeriesDetail(seriesId: Int): Flow<DataState<TvSeriesDetail>>
    fun recommendedTvSeries(seriesId: Int): Flow<DataState<List<TvSeriesItem>>>
    fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>>
}