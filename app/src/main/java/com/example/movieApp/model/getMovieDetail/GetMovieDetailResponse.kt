package com.example.movieApp.model.getMovieDetail


import com.google.gson.annotations.SerializedName

data class GetMovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean?, // false
    @SerializedName("backdrop_path")
    val backdropPath: String?, // /p1F51Lvj3sMopG948F5HsBbl43C.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    @SerializedName("budget")
    val budget: Int?, // 250000000
    @SerializedName("genres")
    val genres: List<Genre?>?,
    @SerializedName("homepage")
    val homepage: String?, // https://movies.disney.id/thor-love-and-thunder
    @SerializedName("id")
    val id: Int?, // 616037
    @SerializedName("imdb_id")
    val imdbId: String?, // tt10648342
    @SerializedName("original_language")
    val originalLanguage: String?, // en
    @SerializedName("original_title")
    val originalTitle: String?, // Thor: Love and Thunder
    @SerializedName("overview")
    val overview: String?, // "Thor: Love and Thunder"menceritakan Thor (Chris Hemsworth) dalam sebuah perjalanan yang belum pernah ia jalani – pencariankedamaian batin. Namun, masa pensiunnya terganggu oleh seorang pembunuh galaksi yang dikenal sebagai Gorr sang Dewa Jagal (Christian Bale), yang ingin memusnahkan para dewa. Untuk mengatasi ancaman, Thor meminta bantuan Raja Valkyrie (Tessa Thompson), Korg (Taika Waititi), dan mantan kekasihnya Jane Foster (Natalie Portman), yang secara mengejutkan dan misterius berhasil menggunakan palu ajaibnya, Mjolnir, sebagai Mighty Thor. Bersama, mereka memulai petualangan kosmik yang mendebarkan untuk mengungkap misteri pembalasan Dewa Jagal dan menghentikannya sebelum terlambat.
    @SerializedName("popularity")
    val popularity: Double?, // 10909.273
    @SerializedName("poster_path")
    val posterPath: String?, // /pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany?>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry?>?,
    @SerializedName("release_date")
    val releaseDate: String?, // 2022-07-06
    @SerializedName("revenue")
    val revenue: Int?, // 698864051
    @SerializedName("runtime")
    val runtime: Int?, // 119
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>?,
    @SerializedName("status")
    val status: String?, // Released
    @SerializedName("tagline")
    val tagline: String?, // Yang satu ini bukan satu-satunya.
    @SerializedName("title")
    val title: String?, // Thor: Cinta dan Guntur
    @SerializedName("video")
    val video: Boolean?, // false
    @SerializedName("vote_average")
    val voteAverage: Double?, // 6.769
    @SerializedName("vote_count")
    val voteCount: Int? // 1739
)