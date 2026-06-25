package com.a2004256_ahmedmohamed.movieapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val auth: FirebaseAuth,
    private val db: DatabaseReference
) : ViewModel() {
    private val _user = MutableStateFlow(UserProfile())
    val user = _user.asStateFlow()

    init {
        observeUser()
    }
    private fun observeUser() {
        val uid = auth.currentUser?.uid ?: return

        viewModelScope.launch {
            db.child("users")
                .child(uid)
                .valueEvents
                .collect { snapshot ->

                    val user = UserProfile(
                        name = snapshot.child("name").value?.toString() ?: "",
                        email = snapshot.child("email").value?.toString() ?: "",
                        bio = snapshot.child("bio").value?.toString() ?: "",
                        image = snapshot.child("image").value?.toString() ?: ""
                    )

                    _user.value = user
                }
        }
    }
    fun saveProfile(user: UserProfile) {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            db.child("users")
                .child(uid)
                .setValue(user)
        }
    }
}