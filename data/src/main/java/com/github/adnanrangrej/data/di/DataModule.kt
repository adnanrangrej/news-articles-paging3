package com.github.adnanrangrej.data.di

import android.app.Application
import androidx.room.Room
import com.github.adnanrangrej.data.local.AppDatabase
import com.github.adnanrangrej.data.remote.NewsApiService
import com.github.adnanrangrej.data.repository.NewsRepositoryImpl
import com.github.adnanrangrej.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {

        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "news_app_db"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApiService {
        return retrofit.create<NewsApiService>()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(database: AppDatabase, apiService: NewsApiService): NewsRepository {
        return NewsRepositoryImpl(database, apiService)
    }

}