package com.example.stormyapiapp

import com.google.gson.annotations.SerializedName
// this ^^^ not in tutorial, but in example files


// this corresponds to 'GetMoviesResponse' in tutorial!   [=
// this is another 'data class', had to add 'data' in front
// and change curlies to parens.....

data class GetTVshowsResponse (

    @SerializedName("page") val page: Int,

    // of course this one was 'List<Movie>'... so i should do this:
    @SerializedName("results") val TVshows: List<TVshow>,

    @SerializedName("total_page") val pages: Int

)