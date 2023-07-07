package com.example.composebasictutorial

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasictutorial.ui.theme.ComposeBasicTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var showLoginScreen by remember { mutableStateOf(true) }
    if (showLoginScreen) {
        LoginScreen(onClick = { showLoginScreen = !showLoginScreen })
    } else {
        ListHandler()
    }

}

@Composable
fun ListHandler(
    modifier: Modifier = Modifier,
    names: List<String> = List(10) { "$it" }
) {
    LazyColumn(modifier.padding(vertical = 12.dp)) {
        items(items = names){ name->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val lastPadding = if (expanded) 40.dp else 0.dp
    Surface(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(bottom = lastPadding)
            ) {
                Text(text = "Hello")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(
                    if (expanded) "Show Less" else "Show More",
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(
            modifier = modifier.padding(vertical = 12.dp),
            onClick = onClick
        ) {
            Text("Continue")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ComposeBasicTutorialTheme {
        LoginScreen(onClick = {})
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBasicTutorialTheme {
        ListHandler()
    }
}