package com.hyun.sesac.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface MyPageNavigationRoute : YeogidaeyoNavigation {
    @Serializable
    data object MyPageTab : MyPageNavigationRoute{
        override val route = "MyPage"
    }
}