package com.example.satelliteprojectcompose.data.room.source

import androidx.room.TypeConverter

class StringToIntConverter {
    @TypeConverter
    fun fromString(value: String?): Int? {
        return value?.toIntOrNull()
    }

    @TypeConverter
    fun toString(value: Int?): String? {
        return value?.toString()
    }
}