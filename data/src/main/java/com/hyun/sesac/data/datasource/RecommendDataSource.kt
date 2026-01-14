package com.hyun.sesac.data.datasource

import android.R.attr.data
import com.hyun.sesac.data.R
import com.hyun.sesac.domain.model.AiRecommendModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

/*
interface RecommendDataSource {
    fun getRecommendList(): Flow<List<AiRecommendModel>>
    suspend fun updateFavorite(id: String)
}

// TODO 12/22 mockdata는 impl로 구현체 만들어주는게 맞음
class MockRecommendDataSource : RecommendDataSource {
    private val _mockData = MutableStateFlow(
        listOf(
            AiRecommendModel(
                id = "1",
                name = "남부초등학교 공영주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "09:00 - 21:00",
                priceInfo = "3,000원 (분당)",
                isFavorite = false
            ),
            AiRecommendModel(
                id = "2",
                name = "서울시청 주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "24시간",
                priceInfo = "무료",
                isFavorite = true
            ),
            AiRecommendModel(
                id = "3",
                name = "동대문 DDP 주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "10:00 - 22:00",
                priceInfo = "4,800원 (시간)",
                isFavorite = false
            )
        )
    )

    // TODO 12/22 data source는 굳이 flow 사용 안해도 됨
    override fun getRecommendList(): Flow<List<AiRecommendModel>> {
        // TODO 12/22 suspend 주고, delay(1000) 줘서 실제 firebase 처럼
        // TODO 12/22 result 잘 안씀 ( 현업에서는 )
        return _mockData.asStateFlow()
    }

    override suspend fun updateFavorite(id: String) {
        _mockData.update { list ->
            list.map { item ->
                if (item.id == id) {
                    item.copy(isFavorite = item.isFavorite)
                } else item
            }
        }
    }
}*/
