package com.hyun.sesac

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hyun.sesac.common.YeogidaeyoApplication
import com.hyun.sesac.ui.screen.EntryScreen
import com.hyun.sesac.ui.theme.YeogidaeyoTheme
import com.kakao.sdk.common.util.Utility
import java.security.MessageDigest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO splash 화면 추가
        enableEdgeToEdge()
        setContent {
            YeogidaeyoTheme {
                EntryScreen()
            }

            /*val keyHash = Utility.getKeyHash(this)
            Log.d("keyHash",keyHash)*/
        }
    }
}
