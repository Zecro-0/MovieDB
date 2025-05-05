package com.example.moviedb.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class MovieDetailResponse(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @Embedded
    @SerialName("belongs_to_collection")
    val belongsToCollection: CollectionInfo? = null,

    val budget: Int,

    val genres: List<Genre>,

    val homepage: String,

    @PrimaryKey
    val id: Int,

    @SerialName("imdb_id")
    val imdbId: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,

    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>,

    @SerialName("release_date")
    val releaseDate: String,

    val revenue: Int,

    val runtime: Int? = null,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    val status: String,

    val tagline: String? = null,

    val title: String,

    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class CollectionInfo(
    @SerialName("id")
    val collectionId: Int,
    @SerialName("name")
    val collectionName: String,

    @SerialName("poster_path")
    val collectionPosterPath: String? = null,

    @SerialName("backdrop_path")
    val collectionBackdropPath: String? = null
)


@Serializable
data class ProductionCompany(
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("id")
    val productionCompanyId: Int,

    @SerialName("name")
    val productionCompanyName: String,

    @SerialName("origin_country")
    val originCountry: String,
)

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1")
    val iso3166_1: String,
    @SerialName("name")
    val productionCountryName: String,
)

@Serializable
data class SpokenLanguage(
    @SerialName("english_name")
    val englishName: String,


    @SerialName("iso_639_1")
    val iso639_1: String,

    @SerialName("name")
    val spokenLanguageName: String,
)