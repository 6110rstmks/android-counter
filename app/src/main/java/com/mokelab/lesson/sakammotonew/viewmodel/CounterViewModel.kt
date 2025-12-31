package com.mokelab.lesson.sakammotonew.viewmodel

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.dataStore by preferencesDataStore(name = "counter_prefs")
private val COUNT_KEY = intPreferencesKey("count")

class CounterViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    val count: Flow<Int> = context.dataStore.data
        .map { prefs -> prefs[COUNT_KEY] ?: 0 }

    fun increment() {
        viewModelScope.launch {
            context.dataStore.edit { prefs ->
                val current = prefs[COUNT_KEY] ?: 0
                prefs[COUNT_KEY] = current + 1
            }
        }
    }

    fun decrement(onBelowZero: () -> Unit) {
        viewModelScope.launch {
            context.dataStore.edit { prefs ->
                val current = prefs[COUNT_KEY] ?: 0
                if (current > 0) {
                    prefs[COUNT_KEY] = current - 1
                } else {
                    onBelowZero()
                }
            }
        }
    }

    fun reset() {
        viewModelScope.launch {
            context.dataStore.edit { prefs ->
                prefs[COUNT_KEY] = 0
            }
        }
    }
}
