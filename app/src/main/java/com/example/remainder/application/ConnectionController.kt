package com.example.remainder.application

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.remainder.database.entity.BaseEntity
import com.example.remainder.database.entity.EntityType
import kotlinx.coroutines.*
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


    private suspend fun httpGet(): String {
        return withContext(Dispatchers.IO) {
            val url = URL(serverURL)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                val stream = BufferedInputStream(conn.inputStream)
                val stringBuilder = StringBuilder()
                BufferedReader(InputStreamReader(stream)).forEachLine { stringBuilder.append(it) }
                App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().setDefault(stringBuilder.toString())
            }
            ""
        }
    }


    private suspend fun httpPost(): String {
        return withContext(Dispatchers.IO) {
            val url = URL(serverURL)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")
            val databaseJsonObject = buildDatabaseJsonObject()
            setPostRequestContent(conn, databaseJsonObject)
            conn.connect()
            conn.responseMessage + ""
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
    private fun setPostRequestContent(conn: HttpURLConnection, databaseJsonObject: JSONObject) {
        val outputStream = conn.outputStream
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
        bufferedWriter.write(databaseJsonObject.toString())
        bufferedWriter.flush()
        bufferedWriter.close()
        outputStream.close()
    }

    private fun buildDatabaseJsonObject(): JSONObject {
        val databaseJSONObject = JSONObject()
        EntityType.values().forEach {
            entityType ->  databaseJSONObject.accumulate(entityType.tableName, this.getTableJsonObject(entityType.tableName))
        }
        return databaseJSONObject
    }

    private fun getTableJsonObject(tableName: String): JSONObject {
        val entityList: List<BaseEntity> = App.globalSharedPreferences.DATABASE_CONTROLLER.getInstance().getAll(tableName)
        val tableJsonObject = JSONObject()
        entityList.forEach { entity -> tableJsonObject.accumulate(tableName, entity.toJsonObject()) }
        return tableJsonObject
    }
}