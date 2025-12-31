package com.mokelab.lesson.sakammotonew.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class AppBlockService : AccessibilityService() {

    // ブロック対象のパッケージ名のリスト（例として設定）
    // 実際には設定画面から変更できるようにするのが望ましいです
    private val blockedPackages = mutableSetOf(
//        "com.android.settings", // 設定アプリ（テスト用）
        "com.google.android.youtube" // YouTube（テスト用）
    )

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: return
            Log.d("AppBlockService", "Detected package: $packageName")

            if (blockedPackages.contains(packageName)) {
                Log.d("AppBlockService", "Blocking package: $packageName")
                // ホーム画面に戻すことでブロックを実現
                performGlobalAction(GLOBAL_ACTION_HOME)
                
                // オプション: 独自のブロック画面を表示したい場合は Intent を発行
                // val intent = Intent(this, MainActivity::class.java).apply {
                //     addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                // }
                // startActivity(intent)
            }
        }
    }

    override fun onInterrupt() {
        Log.d("AppBlockService", "Service Interrupted")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("AppBlockService", "Service Connected")
    }
}
