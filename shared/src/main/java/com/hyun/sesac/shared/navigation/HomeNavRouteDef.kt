package com.hyun.sesac.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavigationRoute : YeogidaeyoNavigation {
    @Serializable
    data object HomeTab : HomeNavigationRoute {
        override val route = "Home"
    }

    @Serializable
    data object SearchScreen : HomeNavigationRoute {
        override val route = "SearchScreen"
    }

    @Serializable
    data object DetailScreen : HomeNavigationRoute {
        override val route = "DetailScreen"
    }
}