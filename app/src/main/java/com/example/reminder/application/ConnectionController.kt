package com.example.reminder.application

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.reminder.database.entity.BaseEntity
import com.example.reminder.database.entity.EntityType
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class ConnectionController {

    companion object {
        private var INSTANCE: ConnectionController? = null
        private var serverURL = "http://168.188.127.185:5604"
        fun getInstance(): ConnectionController {
            if (INSTANCE == null) {
                INSTANCE = ConnectionController()
            }
            return INSTANCE!!
        }
    }

    fun downLoadDefault() {
        if (checkNetworkConnection())
            GlobalScope.launch { httpGet() }
    }

    fun syncWithServer() {
        if (checkNetworkConnection()) {
            GlobalScope.launch { httpPost() }
        }
    }


    private suspend fun httpGet() {
        return withContext(Dispatchers.IO) {
            val url = URL(serverURL)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                val stringBuilder = StringBuilder()
                conn.inputStream.bufferedReader().use { it.lines().forEach { line -> stringBuilder.append(line)} }
                App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().setDefault(stringBuilder.toString())
            }
        }
    }


    private suspend fun httpPost() {
        return withContext(Dispatchers.IO) {
            val url = URL(serverURL)
            val conn = url.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.doOutput = true
            conn.requestMethod = "POST"
            conn.setRequestProperty("Connection", "close")
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")
            val databaseJsonObject = buildDatabaseJsonObject()
            setPostRequestContent(conn, databaseJsonObject)
        }
    }

    private fun setPostRequestContent(conn: HttpURLConnection, databaseJsonObject: JSONObject) {
        val outputStream = conn.outputStream
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
        bufferedWriter.write(databaseJsonObject.toString())
        bufferedWriter.flush()
        if (conn.responseCode == HttpURLConnection.HTTP_OK) {

        }

    }

    private fun checkNetworkConnection(): Boolean {
        val connectManager = App.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectManager.activeNetwork ?: return false
        val actNw = connectManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun buildDatabaseJsonObject(): JSONObject {
        val databaseJSONObject = JSONObject()
        EntityType.values().forEach {
            entityType -> putTableJsonObject(databaseJSONObject, entityType.tableName)
        }
        return databaseJSONObject
    }

    private fun putTableJsonObject(databaseJsonObject: JSONObject, tableName: String) {
        val entityList: List<BaseEntity> = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAll(tableName)
        databaseJsonObject.put(tableName, JSONArray())
        entityList.forEach { entity -> databaseJsonObject.accumulate(tableName, entity.toJsonObject()) }
    }
}