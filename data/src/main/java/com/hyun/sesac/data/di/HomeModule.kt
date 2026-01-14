package com.hyun.sesac.data.di

import com.hyun.sesac.data.datasource.MockRecommendDataSource
import com.hyun.sesac.data.datasource.RecommendDataSource
import com.hyun.sesac.data.impl.RecommendRepositoryImpl
import com.hyun.sesac.domain.repository.RecommendRepository
import com.hyun.sesac.domain.usecase.home.GetRecommendListUseCase
import com.hyun.sesac.domain.usecase.home.GetToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule{
    @Provides
    @Singleton
    fun provideRecommendDataSource(): RecommendDataSource{
        return MockRecommendDataSource()
    }

    // TODO 12/22 datasource는 따로 작성해서 srp 원칙 지키기
    // TODO 12/22 @binds로 묶을 수 있는건 묶기 ( repository 등 ppt에 있음 )
    @Provides
    @Singleton
    fun provideRecommendRepository(
        dataSource: RecommendDataSource
    ): RecommendRepository{
        return RecommendRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideRecommendUseCase(
        repository: RecommendRepository
    ): GetRecommendListUseCase{
        return GetRecommendListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleFavoriteUseCase(
        repository: RecommendRepository
    ): GetToggleFavoriteUseCase{
        return GetToggleFavoriteUseCase(repository)
    }
}