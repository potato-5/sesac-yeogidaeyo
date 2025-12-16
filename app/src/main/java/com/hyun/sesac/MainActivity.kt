package com.hyun.sesac

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

            // 🔑 [KeyHash 추출 코드] 로그캣에서 "KeyHash" 태그로 검색하세요.
            try {
                val info = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                )
                for (signature in info.signatures!!) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val keyHash = Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                    Log.d("KeyHash", "현재 디버그 키 해시: $keyHash")
                }
            } catch (e: Exception) {
                Log.e("KeyHash", "키 해시 추출 실패", e)
            }

            /*val keyHash = Utility.getKeyHash(this)
            Log.d("keyHash",keyHash)*/
        }
    }
}
