package com.hyun.sesac.data.repository

import com.hyun.sesac.data.datasource.ParkingDataSource
import com.hyun.sesac.domain.common.DataResourceResult
import com.hyun.sesac.domain.model.Parking
import com.hyun.sesac.domain.repository.ParkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class FirestoreParkingRepositoryImpl(
    private val dataSource: ParkingDataSource
) : ParkingRepository {

    override fun read() = dataSource.read()
        .map { parkingList ->
            DataResourceResult.Success(parkingList) as DataResourceResult<List<Parking>>
        }
        .catch{ e ->
            emit(DataResourceResult.Failure(e))
        }
        .onStart{ emit(DataResourceResult.Loading)}
        .flowOn(Dispatchers.IO)

    private fun wrapCUDOperation(
        operation: suspend () -> Unit
    ): Flow<DataResourceResult<Unit>> = flow{
        emit(DataResourceResult.Loading)
        operation()
        emit(DataResourceResult.Success(Unit))
    }.catch{ e->
        emit(DataResourceResult.Success(Unit))
    }.flowOn(Dispatchers.IO)

    override fun update(parkingInfo: Parking): wrapCUDOperation {
        dataSource.update(parkingInfo)
    }

    override fun delete(parkingID: String): wrapCUDOperation {
        dataSource.delete(parkingID)
    }

    override fun create(parkingInfo: Parking) = wrapCUDOperation {
        dataSource.create(parkingInfo)
    }
}