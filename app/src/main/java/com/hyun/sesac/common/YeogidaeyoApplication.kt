package com.hyun.sesac.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk

class YeogidaeyoApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        yeogidaeyoApp = this
        fixOrientationPortrait()
        KakaoSdk.init(this, "2635106b67f96e5c3fc879bee650db4f") // TODO secret 에 넣기
        KakaoMapSdk.init(this, "2635106b67f96e5c3fc879bee650db4f")
    }
    companion object{
        private lateinit var yeogidaeyoApp : YeogidaeyoApplication
        fun fetchYeogidaeyoApplication() = yeogidaeyoApp
    }
    // TODO FIREBASE, COIL 등 설정
    // 단말기 화면 세로모드 고정

    private fun fixOrientationPortrait(){
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
        @SuppressLint("SourceLockedOrientationActivity")
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
            })
        }
}