package android.milestone.network.response.auth

import android.milestone.network.model.auth.TeamInfoModel

data class MyPageInfo(
    val user: User?,
    val team: TeamInfoModel?,
    val likeCount: String? = "0",
    val tinderCount: String? = "0",
)