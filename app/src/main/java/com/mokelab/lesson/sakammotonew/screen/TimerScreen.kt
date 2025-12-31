package com.mokelab.lesson.sakammotonew.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mokelab.lesson.sakammotonew.viewmodel.TimerViewModel

@Composable
fun TimerScreen(
    viewModel: TimerViewModel,
    onBack: () -> Unit
) {
    val seconds = viewModel.seconds
    val running = viewModel.running

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        @Suppress("DEPRECATION")
        Text(
            text = "Timer: ${seconds}s",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = { viewModel.start() },
                enabled = !running
            ) { Text("Start") }

            Button(
                onClick = { viewModel.stop() },
                enabled = running
            ) { Text("Stop") }

            Button(onClick = { viewModel.reset() }) { Text("Reset") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("ホームへ戻る")
        }
    }
}
