package com.example.greetingsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingsapp.ui.theme.GreetingsAppTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalizedGreetingApp()
                }
            }
        }
    }
}

@Composable
fun PersonalizedGreetingApp() {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var greeting by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = greeting,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { greeting = generateGreeting(name.text) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Generate Greeting")
        }
    }
}

fun generateGreeting(name: String): String {
    if (name.isBlank()) return "Please enter your name."

    val timeGreeting = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 5..11 -> "Good morning"
        in 12..17 -> "Good afternoon"
        in 18..22 -> "Good evening"
        else -> "You should go to sleep"
    }

    return "$timeGreeting, $name!"
}

@Preview(showBackground = true)
@Composable
fun PersonalizedGreetingAppPreview() {
    GreetingsAppTheme {
        PersonalizedGreetingApp()
    }
}