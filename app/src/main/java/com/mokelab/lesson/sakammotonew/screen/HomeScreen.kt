package com.mokelab.lesson.sakammotonew.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onGoCounter: () -> Unit,
    onGoTimer: () -> Unit,
    onGoAppBlock: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ホーム", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onGoCounter,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("カウンター画面へ")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onGoTimer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("タイマー画面へ")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onGoAppBlock,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("アプリブロック設定へ")
        }

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ログアウト")
        }
    }
}
