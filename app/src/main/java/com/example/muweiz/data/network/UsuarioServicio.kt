package com.example.muweiz.data.network

import com.example.muweiz.data.model.login.UserSignIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioServicio @Inject constructor(private val firebase: FirebaseClient) {
    companion object {
        const val USER_COLLECTION = "users"
    }

    suspend fun createUserTable(userSignIn: UserSignIn) = runCatching {

        val user = hashMapOf(
            "email" to userSignIn.email,
            "nickname" to userSignIn.nickName,
            "realname" to userSignIn.realName
        )

        firebase.db
            .collection(USER_COLLECTION)
            .add(user).await()

    }.isSuccess
}