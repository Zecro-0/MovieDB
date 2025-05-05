package com.example.moviedb.data

import androidx.compose.ui.input.key.type
import androidx.compose.ui.semantics.CollectionInfo
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.moviedb.model.Genre
import com.example.moviedb.model.ProductionCompany
import com.example.moviedb.model.ProductionCountry
import com.example.moviedb.model.SpokenLanguage
import com.example.moviedb.model.Video
import com.google.common.reflect.TypeToken
import kotlinx.serialization.json.Json


class Converters {
    @TypeConverter
    fun fromListStringToString(stringList: List<String>): String = stringList.toString()
    @TypeConverter
    fun toListStringFromString(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (e: Exception) {

            }
        }
        return result
    }

    @TypeConverter
    fun fromListIntToString(intList: List<Int>): String = intList.toString()
    @TypeConverter
    fun toListIntFromString(stringList: String): List<Int> {
        val result = ArrayList<Int>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n.toInt())
            } catch (e: Exception) {

            }
        }
        return result
    }


    @TypeConverter
    fun fromGenreList(value: String): List<Genre> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toGenreList(list: List<Genre>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromProductionCompanyList(value: String): List<ProductionCompany> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toProductionCompanyList(list: List<ProductionCompany>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromProductionCountryList(value: String): List<ProductionCountry> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toProductionCountryList(list: List<ProductionCountry>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromSpokenLanguageList(value: String): List<SpokenLanguage> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toSpokenLanguageList(list: List<SpokenLanguage>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromVideoList(value: String): List<Video> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toVideoList(list: List<Video>): String {
        return Json.encodeToString(list)
    }


    @TypeConverter
    fun fromCollectionInfo(value: String): CollectionInfo? {
        return Json.decodeFromString(value)
    }
    @TypeConverter
    fun toCollectionInfo(info: CollectionInfo?): String{
        return Json.encodeToString(info)
    }
}