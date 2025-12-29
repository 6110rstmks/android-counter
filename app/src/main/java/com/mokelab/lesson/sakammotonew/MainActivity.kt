package com.mokelab.lesson.sakammotonew

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.mokelab.lesson.sakammotonew.ui.theme.SakammotonewTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map

// DataStore（ファイル名）
private val Context.dataStore by preferencesDataStore(name = "counter_prefs")

// 保存するキー
private val COUNT_KEY = intPreferencesKey("count")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SakammotonewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
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
            text = { Text("マイナdスにはできません") },
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
        Text(text = "Count: $count")

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                if (count > 0) saveCount(count - 1) else showDialog = true
            }) { Text("-") }

            Button(onClick = { saveCount(0) }) { Text("Reset") }

            Button(onClick = { saveCount(count + 1) }) { Text("+") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    SakammotonewTheme {
        // PreviewではDataStoreが絡むので表示が不安定になりがち。必要なら別Preview用UIを作るのが安全。
        Text("Preview")
    }
}
