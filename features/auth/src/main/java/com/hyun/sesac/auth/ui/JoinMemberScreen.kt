package com.hyun.sesac.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun JoinMemberScreen(){
    Column {
        Text(
            text="회원가입 화면",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}