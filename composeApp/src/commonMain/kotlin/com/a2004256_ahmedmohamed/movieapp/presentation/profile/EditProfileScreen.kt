package com.a2004256_ahmedmohamed.movieapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import org.koin.compose.koinInject

data class EditProfileScreen(
    val currentUser: UserProfile
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val vm: ProfileViewModel = koinInject()
        EditProfileScreenContent(
            vm = vm,
            currentUser = currentUser,
            onDone = { navigator.pop() }
        )
    }
}

@Composable
fun EditProfileScreenContent(
    vm: ProfileViewModel,
    currentUser: UserProfile,
    onDone: () -> Unit
) {

    var name by remember { mutableStateOf(currentUser.name) }
    var bio by remember { mutableStateOf(currentUser.bio) }
    var image by remember { mutableStateOf(currentUser.image) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {

        Text("Edit Profile", color = Color.White, fontSize = 22.sp)

        Spacer(Modifier.height(16.dp))

        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Bio") },
            minLines = 3
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                vm.saveProfile(
                    UserProfile(
                        name = name,
                        bio = bio,
                        image = image
                    )
                )
                onDone()
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF8F86FF),
                            Color(0xFFF7A9B8)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .height(48.dp)
        ) {
            Text("Save Profile")
        }
    }
}