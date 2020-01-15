package com.example.reminder.application

import android.content.Context
import android.content.SharedPreferences

class GlobalSharedPreferences(context: Context) {

    var USER_NAME: String? = "hoon"
    val PREFS_FILENAME = "appData"
    val PREF_KEY_LOCK = "Lock"
    val DATABASE_CONTROLLER = DatabaseController
    val CONNECTION_CONTROLLER = ConnectionController.getInstance()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    /* 파일 이름과 EditText를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */

    var lockScreen: Boolean
        get() = sharedPreferences.getBoolean(PREF_KEY_LOCK, true)
        set(value) = sharedPreferences.edit().putBoolean(PREF_KEY_LOCK, value).apply()
    /* get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 ""
     * set(value) 실행 시 value로 값을 대체한 후 저장 */
}