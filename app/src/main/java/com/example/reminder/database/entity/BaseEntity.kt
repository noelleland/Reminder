package com.example.reminder.database.entity

import org.json.JSONObject

interface BaseEntity {
    fun toJsonObject(): JSONObject
}