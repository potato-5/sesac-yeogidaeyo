package com.hyun.sesac.common

import android.app.Application

class YeogidaeyoApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        yeogidaeyoApp = this
    }
    companion object{
        private lateinit var yeogidaeyoApp : YeogidaeyoApplication
        fun fetchYeogidaeyoApplication() = yeogidaeyoApp
    }
}