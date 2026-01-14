package com.hyun.sesac.mypage.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyun.sesac.shared.ui.component.CommonIcon
import com.hyun.sesac.shared.ui.component.CommonWrapperCard

@Composable
fun FavoriteItemSection(
    modifier: Modifier = Modifier,
    favoriteParkingName: String,
){
    CommonWrapperCard(
        modifier = modifier.padding(top = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            CommonIcon(
                icon = Icons.Default.Favorite,
                iconPadding = 16.dp,
                iconColor = Color(0xFF6200EE),
                modifier = Modifier
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = favoriteParkingName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun FavoriteItemPreview() {
    MaterialTheme {
        Scaffold { innerPadding ->
            FavoriteItemSection(
                modifier = Modifier
                    .padding(innerPadding),
                favoriteParkingName = "서울시청 본청사 주차장"
            )
        }
    }
}