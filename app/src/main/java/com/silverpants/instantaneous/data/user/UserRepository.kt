package com.silverpants.instantaneous.data.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.silverpants.instantaneous.data.user.models.FirebaseUserInfo
import com.silverpants.instantaneous.data.user.sources.FirebaseUserDataSource
import com.silverpants.instantaneous.misc.Result
import com.silverpants.instantaneous.misc.suspendAndWait
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDataSource: FirebaseUserDataSource,
    val auth: FirebaseAuth
) {

    @ExperimentalCoroutinesApi
    suspend fun updateUserDisplayName(displayName: String) {
        val changeRequest = UserProfileChangeRequest.Builder().setDisplayName(displayName).build()
        auth.currentUser?.updateProfile(changeRequest)?.suspendAndWait()
    }

    @ExperimentalCoroutinesApi
    fun getBasicUserInfo(): Flow<Result<FirebaseUserInfo>> {
        return userDataSource.getBasicUserInfo()
    }
}