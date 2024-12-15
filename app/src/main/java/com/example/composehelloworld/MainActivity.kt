package com.example.composehelloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            
            MaterialTheme(
                colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        onThemeChange = { isDarkTheme = !isDarkTheme }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onThemeChange: () -> Unit) {
    var count by remember { mutableStateOf(0) }
    var greeting by remember { mutableStateOf("Hello Android!") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = greeting,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.animateContentSize()
        )
        
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.bodyLarge
        )
        
        Button(
            onClick = { count++ }
        ) {
            Text("Increment Counter")
        }
        
        Button(
            onClick = {
                greeting = if (greeting == "Hello Android!") {
                    "Welcome to Compose!"
                } else {
                    "Hello Android!"
                }
            }
        ) {
            Text("Change Greeting")
        }
        
        Switch(
            checked = MaterialTheme.colorScheme.isLight(),
            onCheckedChange = { onThemeChange() }
        )
    }
}

@Composable
fun ColorScheme.isLight() = this == lightColorScheme()