package com.mokelab.lesson.sakammotonew.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mokelab.lesson.sakammotonew.viewmodel.CounterViewModel

@Composable
fun CounterScreen(
    viewModel: CounterViewModel,
    onGoTimer: () -> Unit,
    onBack: () -> Unit
) {
    // ViewModelからデータを収集
    val count by viewModel.count.collectAsState(initial = 0)

    // ダイアログ表示
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("注意") },
            text = { Text("マイナスにはできません") },
            confirmButton = {
                TextButton(onClick = { showDialog.value = false }) { Text("OK") }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        @Suppress("DEPRECATION")
        Text(text = "Count: $count", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                viewModel.decrement(onBelowZero = { showDialog.value = true })
            }) { Text("-") }

            Button(onClick = { viewModel.reset() }) { Text("Reset") }

            Button(onClick = { viewModel.increment() }) { Text("+") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onGoTimer, modifier = Modifier.fillMaxWidth()) {
            Text("タイマー画面へ")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("ホームへ戻る")
        }
    }
}
