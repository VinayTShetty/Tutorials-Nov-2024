package com.androidtutorials.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BooleanKeyLaunchedEffectDemo()
                }
            }
        }
    }
}

@Composable
fun BooleanKeyLaunchedEffectDemo() {

    var counter by remember { mutableStateOf(0) }
    var dummyCounter by remember { mutableStateOf(0) }

    val isEven = counter % 2 == 0

    // 🔥 Runs ONLY when 'isEven' changes (false → true or true → false)
    LaunchedEffect(isEven) {
        println("🔥 LaunchedEffect executed: isEven = $isEven")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Counter = $counter",
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "isEven = $isEven",
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = "dummyCounter = $dummyCounter",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(modifier = Modifier.height(25.dp))

            // KEY CHANGES → LaunchedEffect runs
            Button(onClick = { counter++ }) {
                Text("Increase Counter (Change Key)", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(15.dp))

            // RECOMPOSITION ONLY → LaunchedEffect will NOT run
            Button(onClick = { dummyCounter++ }) {
                Text("Recompose WITHOUT Key Change", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDemo() {
    MyApplicationTheme {
        BooleanKeyLaunchedEffectDemo()
    }
}
