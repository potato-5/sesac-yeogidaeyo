package com.hyun.sesac.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.ui.graphics.vector.ImageVector
import com.hyun.sesac.shared.navigation.BottomNavigationRoute
import com.hyun.sesac.shared.navigation.YeogidaeyoNavigation

data class BottomAppBarItem(
    val tabName: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val destination: YeogidaeyoNavigation
    ){

    companion object{
        fun fetchBottomAppBarItems() = listOf(
            BottomAppBarItem(
                tabName = "Home",
                icon = Icons.Filled.Home,
                destination = BottomNavigationRoute.HomeTab
            ),
            BottomAppBarItem(
                tabName = "Register",
                icon = Icons.Filled.AddCircleOutline,
                destination = BottomNavigationRoute.HomeTab
            ),
            BottomAppBarItem(
                tabName = "MyPage",
                icon = Icons.Filled.PersonOutline,
                destination = BottomNavigationRoute.HomeTab
            )
        )
    }
}