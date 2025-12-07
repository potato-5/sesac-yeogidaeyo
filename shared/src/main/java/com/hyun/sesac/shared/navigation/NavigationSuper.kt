package com.hyun.sesac.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface YeogidaeyoNavigation{
    val route: String
}