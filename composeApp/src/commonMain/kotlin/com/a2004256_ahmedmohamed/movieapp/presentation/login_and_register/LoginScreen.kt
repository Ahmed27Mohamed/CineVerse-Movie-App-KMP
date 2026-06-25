package com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cineverse.composeapp.generated.resources.Res
import cineverse.composeapp.generated.resources.google
import cineverse.composeapp.generated.resources.on1
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeScreen
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        LoginScreenContent()
    }
}

@Composable
fun LoginScreenContent() {

    val navigator = LocalNavigator.currentOrThrow
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val auth: AuthRepository = koinInject()
    val auth2: FirebaseAuth = koinInject()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val user = auth2.currentUser
        if (user != null) {
            navigator.replace(HomeScreen())
        } else {
            navigator.replace(LoginScreen())
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF09090B),
                        Color(0xFF111827),
                        Color(0xFF09090B)
                    )
                )
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF8F86FF).copy(alpha = .15f),
                            Color.Transparent
                        ),
                        radius = 1200f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "🎬",
                fontSize = 40.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "CineVerse",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 34.sp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF8F86FF),
                            Color(0xFFF7A9B8)
                        )
                    )
                )
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Curating cinematic excellence",
                color = Color(0xFFB3B3B3),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(28.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF121212)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                tint = Color.Gray,
                                contentDescription = ""
                            )
                        },
                        placeholder = {
                            Text("Email")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Black,
                            unfocusedContainerColor = Color.Black,
                            focusedBorderColor = Color(0xFF2A2A2A),
                            unfocusedBorderColor = Color(0xFF2A2A2A),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    Spacer(Modifier.height(18.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                tint = Color.Gray,
                                contentDescription = ""
                            )
                        },
                        placeholder = {
                            Text("Password")
                        },
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                    tint = Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Black,
                            unfocusedContainerColor = Color.Black,
                            focusedBorderColor = Color(0xFF2A2A2A),
                            unfocusedBorderColor = Color(0xFF2A2A2A),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = {
                            isLoading = true
                            scope.launch {
                                val success = auth.login(email, password)
                                if (success) {
                                    isLoading = false
                                    navigator.push(HomeScreen())
                                } else {
                                    isLoading = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(),
                        shape = RoundedCornerShape(14.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFF7B61FF),
                                            Color(0xFFFF4D8D)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator()
                            } else {
                                Text(
                                    "Login",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = Color.DarkGray
                        )

                        Text(
                            text = "  OR  ",
                            color = Color.Gray
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = Color.DarkGray
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    OutlinedButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Continue with Google")
                        Spacer(Modifier.width(10.dp))
                        Image(
                            painter = painterResource(Res.drawable.google),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Don't have an account? ",
                        color = Color.LightGray,
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        text = "Sign up",
                        color = Color(0xFFF7A9B8),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .alignByBaseline()
                            .clickable {
                                navigator.push(RegisterScreen())
                            }
                    )
                }
            }
        }
    }
}