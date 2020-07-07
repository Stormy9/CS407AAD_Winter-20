package com.example.stormyapiapp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// these ^^^ not in tutorial -- but in example files   [=

object TVshowsRepository {

    // tutorial step #5:
    // altered this to match my interface name
    private val api: I_TVshow_API

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(I_TVshow_API::class.java)
        // pretty sure i adapted this ^^^ correctly.....   [=
    }
    //===================================================================================
    // tutorial step #6:
    fun getFavoriteTVshows(
        page: Int=1,
        onSuccess: (TVshows: List<TVshow>)->Unit,   // from 'Callbacks' step 1
        onError: ()->Unit
    ){
            api.getFavoriteTVshows(page = page)
                .enqueue(object : Callback<GetTVshowsResponse> {
                    override fun onResponse(
                        call: Call<GetTVshowsResponse>,
                        response: Response<GetTVshowsResponse>
                    ) {
                        if(response.isSuccessful) {
                            val responseBody = response.body()

                            if(responseBody != null) {
                                //Log.d("Repository", "TV shows: ${responseBody.TVshows}")
                                onSuccess.invoke(responseBody.TVshows)
                            } else {
                                //Log.d("Repository", "couldn't get response")
                                onError.invoke()
                            }
                        }
                    }
                    override fun onFailure(call: Call<GetTVshowsResponse>, t: Throwable) {
                        //Log.e("Repository", "onFailure", t)
                        onError.invoke()
                    }
            })
    }
    //===================================================================================
    // Feature Two! -- Part One.....
    fun getWatchListTV(
        page: Int = 1,
        onSuccess: (TVshows: List<TVshow>)->Unit,
        onError: ()->Unit
    ){
        api.getWatchListTV(page=page)
            .enqueue(object: Callback<GetTVshowsResponse> {
                override fun onResponse(
                    call: Call<GetTVshowsResponse>,
                    response: Response<GetTVshowsResponse>
                ){
                    if(response.isSuccessful){
                        val responseBody = response.body()

                        if(responseBody != null) {
                            onSuccess.invoke(responseBody.TVshows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVshowsResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
    //-----------------------------------------------------------------------------------
    // Feature Two! -- Part Two.....
    fun getAiringToday(
        page: Int=1,
        onSuccess:(TVshows: List<TVshow>)->Unit,
        onError: ()->Unit
    ){
        api.getAiringToday(page=page)
            .enqueue(object: Callback<GetTVshowsResponse> {
                override fun onResponse(
                    call: Call<GetTVshowsResponse>,
                    response: Response<GetTVshowsResponse>
                ){
                    if(response.isSuccessful){
                        val responseBody = response.body()

                        if(responseBody !=null){
                            onSuccess.invoke(responseBody.TVshows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVshowsResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}
//=======================================================================================
// from tutorial -- step 5:
// Take note that it uses the object keyword of `Kotlin`,
// which is an easy way to declare a `Singleton` in Kotlin.
//     what??  where??
//
// Using the `init` block of Kotlin,
// which is called when an instance is initialized,
// we instantiate a Retrofit instance using it's builder.
// Then, instantiate an instance of `I_TVshow_API` using the Retrofit instance
//
//---------------------------------------------------------------------------------------
// from tutorial -- step 6 (adding `fun getPopularTVshows())`:
// first, we execute `api.getPopularTVshows()` asynchronously using `enqueue()` method
// then, we log the TV shows if the response was successful.
//
//---------------------------------------------------------------------------------------
// from tutorial -- step 1 of the 'Callbacks' section:
// `onSuccess` is a parameter that doesn't return anything `->Unit` .....
//      but it accepts a list of movies.
// `onError` is the same with onSuccess but it doesn't accept anything.
// All we need to is to just invoke this method.  How do we use it?
//
// in your `getFavoriteTVshows()` method, remove the logs, and replace it with
//      invocations of the functions.....
//
// `invoke()` is how you execute a higher-order function.
//      take note that it will vary depending if the higher-order function
//      has parameters or not.
//      You can see the difference by comparing `onSuccess.invoke(responseBody.TVshows)`
//      and `onError.invoke()`.....
//          `onSuccess: (TVshows: List<TVshow>)->Unit`
//          is to `onSuccess.invoke(responseBody.TVshows)`
//
//          'onError: ()->Unit`
//          is to `onError.invoke()`
//=======================================================================================