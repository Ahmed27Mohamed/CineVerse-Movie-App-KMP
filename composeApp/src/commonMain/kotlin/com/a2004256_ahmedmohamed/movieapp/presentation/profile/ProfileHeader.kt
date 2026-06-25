package com.a2004256_ahmedmohamed.movieapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import kotlin.io.encoding.Base64

@Composable
fun ProfileHeader(
    user: UserProfile,
    rank: String,
    onEditClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box {

            AsyncImage(
                model = user.image,
                contentDescription = null,
                modifier = Modifier
                    .size(110.dp)
                    .fillMaxWidth()
                    .clip(CircleShape)
            )

            IconButton(
                onClick = onEditClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(32.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null)
            }
        }

        Spacer(Modifier.height(12.dp))

        Text(user.name, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)

        Text(
            "$rank • ${user.email}",
            color = Color.LightGray
        )

        Spacer(Modifier.height(8.dp))

        Text(
            user.bio,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = onEditClick,
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
            Text("Edit Profile", color = Color.Black)
        }
    }
}