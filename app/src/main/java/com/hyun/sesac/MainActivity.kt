package com.hyun.sesac

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.hyun.sesac.common.YeogidaeyoApplication
import com.hyun.sesac.ui.screen.EntryScreen
import com.hyun.sesac.ui.theme.YeogidaeyoTheme
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import java.security.MessageDigest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO splash 화면 추가
        // TODO Hilt view model 선언 해주면 owner는 activity ( activity가 끝날 때 같이 끝남 )
        enableEdgeToEdge()
        setContent {
           // val globalViewModel: AppViewModel = hiltViewModel()
           // navhost 전에 호출
            YeogidaeyoTheme {
                EntryScreen()
            }

            /*val keyHash = Utility.getKeyHash(this)
            Log.d("keyHash",keyHash)*/
        }
    }
}
