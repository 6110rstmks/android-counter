package com.mokelab.lesson.sakammotonew.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var password by mutableStateOf("")
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    // エラーメッセージ管理用の状態を追加
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onPasswordChange(newValue: String) {
        password = newValue
    }

    fun login() {
        if (password == "password123") {
            loginSuccess = true
            errorMessage = null
        } else {
            // 直接 Toast は使わず、メッセージを保持する
            errorMessage = "ログインに失敗しました"
            loginSuccess = false
        }
    }

    fun onNavigated() {
        loginSuccess = false
    }

    // メッセージ表示後にクリアするための関数
    fun consumeErrorMessage() {
        errorMessage = null
    }
}
