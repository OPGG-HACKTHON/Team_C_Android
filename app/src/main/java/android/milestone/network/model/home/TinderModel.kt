package android.milestone.network.model.home

import android.milestone.network.model.auth.UserModel

data class TinderModel(
    val createdAt: String,
    val dislike: Int,
    val gameId: Int,
    val id: Int,
    val like: Int,
    val message: String,
    val pass: Int,
    val superlike: Int,
    val team: TeamModel,
    val user: UserModel
)