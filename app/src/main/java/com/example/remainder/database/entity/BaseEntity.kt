package com.example.remainder.database.entity

import org.json.JSONObject

interface BaseEntity {
    fun toJsonObject(): JSONObject
}