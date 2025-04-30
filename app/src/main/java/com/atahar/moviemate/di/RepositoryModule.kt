package com.piashcse.hilt_mvvm_compose_movie.di

import com.atahar.moviemate.data.datasource.remote.ApiService
import com.atahar.moviemate.data.repository.remote.movie.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideMovieRepository(
        apiService: ApiService,
    ): MovieRepository {
        return MovieRepository(
            apiService
        )
    }
    /**
     * Provides RemoteDataRepository for access api service method
     */
/*    @Singleton
    @Provides
    fun provideTvSeriesRepository(
        apiService: ApiService,
    ): TvSeriesRepository {
        return TvSeriesRepository(
            apiService
        )
    }*/

}