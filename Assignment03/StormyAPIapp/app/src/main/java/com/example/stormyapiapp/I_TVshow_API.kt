package com.example.stormyapiapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
// these ^^^ weren't in tutorial but were in example files


interface I_TVshow_API {

    @GET("account/stormy9/favorite/tv")   // edit to my list of favorite TV shows
                                                // seems to have worked!   [=
    fun getFavoriteTVshows(

        @Query("api_key") apiKey: String = "bc8a1b1ae2a21e3578978c09e457ae91",
        @Query("session_id") sessionID: String = "a9e7f725ac1c093884eb73c6f1e7b8339d5c2cdd",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "created_at.asc"

    ): Call<GetTVshowsResponse>
    // obviously changed this (from tutorial) to match my data class!

    //===================================================================================
    // Feature Two!
    @GET("account/stormy9/watchlist/tv")
    fun getWatchListTV(
        @Query("api_key") apiKey: String = "bc8a1b1ae2a21e3578978c09e457ae91",
        @Query("session_id") sessionID: String = "a9e7f725ac1c093884eb73c6f1e7b8339d5c2cdd",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "created_at.asc"
    ): Call<GetTVshowsResponse>

    //-----------------------------------------------------------------------------------
    @GET("tv/airing_today")
    fun getAiringToday(
        @Query("api_key") apiKey: String = "bc8a1b1ae2a21e3578978c09e457ae91",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int
    ): Call<GetTVshowsResponse>
}
//=======================================================================================
// from tutorial feature 2 step #1
// as you can see, getWatchListTV() and getFavoriteTVshows() are basically the same...
// the only difference is that getWatchListTV() has a different endpoint --
//    @GET("account/stormy9/watchlist/tv")
//---------------------------------------------------------------------------------------