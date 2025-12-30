package com.mokelab.lesson.sakammotonew.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var seconds by remember { mutableStateOf(0) }
    var running by remember { mutableStateOf(false) }

    // running が true の間だけ 1秒ごとに加算
    LaunchedEffect(running) {
        while (running) {
            delay(1000)
            seconds += 1
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Timer: ${seconds}s",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = { running = true },
                enabled = !running
            ) { Text("Start") }

            Button(
                onClick = { running = false },
                enabled = running
            ) { Text("Stop") }

            Button(onClick = { seconds = 0 }) { Text("Reset") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = onBack) {
            Text("戻る")
        }
    }
}
