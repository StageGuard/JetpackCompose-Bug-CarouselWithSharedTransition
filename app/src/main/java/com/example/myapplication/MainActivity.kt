package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                Box(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .statusBarsPadding()
                        .padding(16.dp)
                ) {
                    SharedTransitionLayout {
                        NavHost(navController, NavRoute.Main) {
                            composable<NavRoute.Main> {
                                MainPage(
                                    this,
                                    onClickImage = {
                                        navController.navigate(NavRoute.Second(it))
                                    }
                                )
                            }
                            composable<NavRoute.Second> {
                                val route = it.toRoute<NavRoute.Second>()
                                SecondPage(
                                    route.imageUrl,
                                    this,
                                    onGoBack = { navController.navigateUp() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
sealed class NavRoute {
    @Serializable
    data object Main : NavRoute()
    @Serializable
    class Second(val imageUrl: String) : NavRoute()
}

object SharedTransitionKey {
    const val BOUND = "key-bound"
    const val IMAGE = "key-image"
}