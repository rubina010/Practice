package com.example.letspractice.utils

import androidx.room.TypeConverter
import com.example.letspractice.entity.Items
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Items> {
        val listType = object : TypeToken<List<Items>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<Items>): String {
        val gson = Gson()
        val json: String = gson.toJson(list)
        return json
    }
}