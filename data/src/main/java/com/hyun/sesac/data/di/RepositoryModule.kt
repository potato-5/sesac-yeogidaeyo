package com.hyun.sesac.data.di

import com.hyun.sesac.data.remote.firebase.FirestoreParkingDataSourceImpl
import com.hyun.sesac.data.repository.FirestoreParkingRepositoryImpl
import com.hyun.sesac.domain.repository.ParkingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindParkingRepository(
        impl: FirestoreParkingRepositoryImpl
    ): ParkingRepository
}