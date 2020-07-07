package com.example.stormyapiapp

import com.google.gson.annotations.SerializedName
// ^^^ this wasn't in tutorial -- but WAS in the example file   [=


// this is a 'data class'... came as just 'class TVshow'
// in the tutorial, this is called 'Movie', of course!

data  class TVshow (
    // and these ^^^ came as curlies but apparently we change them to parens

    // of course we will change these later for my TV show stuff   [=
    //   or do i have to?  check Postman -- maybe not (or at least not too much!)
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String

)