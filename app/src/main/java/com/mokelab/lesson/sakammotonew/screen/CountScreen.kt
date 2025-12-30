package com.mokelab.lesson.sakammotonew.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// DataStore（ファイル名）
private val Context.dataStore by preferencesDataStore(name = "counter_prefs")

// 保存するキー
private val COUNT_KEY = intPreferencesKey("count")

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    onGoTimer: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // DataStoreから読み込んで画面に反映
    val count by context.dataStore.data
        .map { prefs -> prefs[COUNT_KEY] ?: 0 }
        .collectAsState(initial = 0)

    // ダイアログ表示
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("注意") },
            text = { Text("マイナスにはできません") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) { Text("OK") }
            }
        )
    }

    fun saveCount(newValue: Int) {
        scope.launch {
            context.dataStore.edit { prefs ->
                prefs[COUNT_KEY] = newValue
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: $count", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                if (count > 0) saveCount(count - 1) else showDialog = true
            }) { Text("-") }

            Button(onClick = { saveCount(0) }) { Text("Reset") }

            Button(onClick = { saveCount(count + 1) }) { Text("+") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onGoTimer) {
            Text("タイマー画面へ")
        }
    }
}
