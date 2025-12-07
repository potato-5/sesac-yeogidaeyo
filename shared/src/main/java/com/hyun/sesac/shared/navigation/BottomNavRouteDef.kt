package com.hyun.sesac.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface BottomNavigationRoute : YeogidaeyoNavigation{
    @Serializable
    data object HomeTab : BottomNavigationRoute

    @Serializable
    data object registerTab : BottomNavigationRoute

    @Serializable
    data object mypageTab : BottomNavigationRoute
}