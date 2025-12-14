package com.hyun.sesac.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.ui.graphics.vector.ImageVector
import com.hyun.sesac.shared.navigation.HomeNavigationRoute
import com.hyun.sesac.shared.navigation.MyPageNavigationRoute
import com.hyun.sesac.shared.navigation.RegisterNavigationRoute
import com.hyun.sesac.shared.navigation.YeogidaeyoNavigation

data class BottomAppBarItem(
    val tabName: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val destination: YeogidaeyoNavigation
    ){

    companion object{
        fun fetchBottomAppBarItems() = listOf(
            BottomAppBarItem(
                tabName = "홈",
                icon = Icons.Filled.Home,
                destination = HomeNavigationRoute.HomeTab
            ),
            BottomAppBarItem(
                tabName = "주차 등록",
                icon = Icons.Filled.AddCircleOutline,
                destination = RegisterNavigationRoute.RegisterTab
            ),
            BottomAppBarItem(
                tabName = "내 설정",
                icon = Icons.Filled.PersonOutline,
                destination = MyPageNavigationRoute.MyPageTab
            )
        )
    }
}