package com.mokelab.lesson.sakammotonew.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mokelab.lesson.sakammotonew.viewmodel.AppBlockViewModel

@Composable
fun AppBlockScreen(
    viewModel: AppBlockViewModel,
    onBack: () -> Unit
) {
    var isEnabled by remember { mutableStateOf(viewModel.isAccessibilityServiceEnabled()) }

    // 画面に戻ってきたときに状態を再確認するための簡易的なフラグ
    LaunchedEffect(Unit) {
        isEnabled = viewModel.isAccessibilityServiceEnabled()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "アプリブロック設定", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (isEnabled) MaterialTheme.colorScheme.primaryContainer 
                                 else MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (isEnabled) "サービスは有効です" else "サービスが無効です",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "アプリをブロックするにはアクセシビリティサービスを有効にする必要があります。",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.openAccessibilitySettings() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("アクセシビリティ設定を開く")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.openUsageStatsSettings() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("利用統計設定を開く")
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ホームへ戻る")
        }
    }
}
