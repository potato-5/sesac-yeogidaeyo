package com.hyun.sesac.data.di

import android.content.Context
import com.hyun.sesac.data.repository.CurrentLocationRepositoryImpl
import com.hyun.sesac.domain.repository.CurrentLocationRepository
import com.hyun.sesac.domain.usecase.map.ObserveLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// TODO 12/22 각 모듈 분리 ( service, repository, data source 이렇게 )
@Module
@InstallIn(SingletonComponent::class)
object MapModule {
    @Provides
    @Singleton
    fun provideCurrentLocationRepository(
        @ApplicationContext context: Context
    ): CurrentLocationRepository{
        return CurrentLocationRepositoryImpl(context)
    }

    // TODO 12/22 usecase는 여기 있으면 안됨
    // TODO 12/22 repository 따로 분리하기 srp 원칙 -> return 값이 두개면 안됨
    // TODO 12/22 느슨한 결합으로 해야 됨 / usecase는 inject 로 결합
}