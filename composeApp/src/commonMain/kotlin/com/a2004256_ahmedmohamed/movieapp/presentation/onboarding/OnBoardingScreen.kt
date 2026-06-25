package com.a2004256_ahmedmohamed.movieapp.presentation.onboarding


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cineverse.composeapp.generated.resources.Res
import cineverse.composeapp.generated.resources.on1
import cineverse.composeapp.generated.resources.on2
import cineverse.composeapp.generated.resources.on3
import coil3.compose.AsyncImage
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

class OnBoardingScreen(
    private val onFinish: () -> Unit
) : Screen {

    @Composable
    override fun Content() {
        OnBoardingContent(onFinish)
    }
}

@Composable
fun OnBoardingContent(
    onFinish: () -> Unit
) {

    val pages = listOf(
        OnBoardingPage(
            title = "Mood-Based Curation",
            description = "Find movies based on your mood instantly",
            image = Res.drawable.on1,
            color = Color(0xFF9C6BFF)
        ),
        OnBoardingPage(
            title = "Build Your Legacy",
            description = "Save and track your favorite movies",
            image = Res.drawable.on2,
            color = Color(0xFF9C6BFF)
        ),
        OnBoardingPage(
            title = "Your AI Film Critic",
            description = "AI recommends movies you will love",
            image = Res.drawable.on3,
            color = Color(0xFF9C6BFF)
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )
    val scope = rememberCoroutineScope()
    val BackgroundBrush = Brush.radialGradient(
        colors = listOf(
            Color(0xFF5B21B6).copy(alpha = 0.35f),
            Color(0xFF1E1B4B).copy(alpha = 0.25f),
            Color(0xFF09090B)
        ),
        radius = 1500f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(top = 24.dp)
            .background(BackgroundBrush)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val item = pages[page]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CineVerse",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 28.sp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF8F86FF),
                                Color(0xFFF7A9B8)
                            )
                        )
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(item.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = item.description,
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pages.size) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(
                                    if (pagerState.currentPage == it) 28.dp
                                    else 10.dp,
                                    10.dp
                                )
                                .clip(RoundedCornerShape(50))
                                .background(
                                    if (pagerState.currentPage == it)
                                        item.color
                                    else
                                        Color.Gray
                                )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        if (pagerState.currentPage == pages.lastIndex) {
                            onFinish()
                        } else {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .navigationBarsPadding()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF8F86FF),
                                    Color(0xFFF7A9B8)
                                )
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.lastIndex)
                            "Get Started"
                        else
                            "Next",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}