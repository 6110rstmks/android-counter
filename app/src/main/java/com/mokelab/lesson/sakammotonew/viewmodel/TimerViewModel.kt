package com.mokelab.lesson.sakammotonew.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {
    var seconds by mutableStateOf(0)
        private set

    var running by mutableStateOf(false)
        private set

    private var timerJob: Job? = null

    fun start() {
        if (running) return
        running = true
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                seconds++
            }
        }
    }

    fun stop() {
        running = false
        timerJob?.cancel()
    }

    fun reset() {
        seconds = 0
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
