package com.unj.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.unj.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsCodelabTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyAppWidget(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // 1. Create the NavController
    val navController = rememberNavController()

    // 2. Create the NavHost
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // Set the initial screen
    ) {
        // 3. Define the screens (destinations)
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.Detail.route) {
            DetailScreen(navController = navController)
        }
    }
}


@Composable
fun MyAppWidget(modifier: Modifier = Modifier){
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        /*if (shouldShowOnboarding) {
            BoardingScreen.OnBoardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }*/
        CalculatorScreen.SetupLayout()
        //ColumnSetup("Me")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        CalculatorScreen.SetupLayout()
    }
    /**Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }*/
}