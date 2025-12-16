package com.hyun.sesac.data.remote

import com.hyun.sesac.data.impl.KakaoApiRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.jvm.java

object KakaoApiClient {

    private const val BASE_URL = "https://dapi.kakao.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    val kakaoApi: KakaoApiRepository by lazy {
        retrofit.create(KakaoApiRepository::class.java)
    }
}
