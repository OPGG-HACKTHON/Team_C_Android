package android.milestone.network.response.home

import android.milestone.network.model.home.CurrentGameModel

data class CurrentGameResponse(
    val data: CurrentGameModel?,
    val status: Int,
    val success: Boolean
)