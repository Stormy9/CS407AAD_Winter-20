package com.example.stormyapiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var favoriteTVshows: RecyclerView
    private lateinit var favoriteFavoriteTVshowsAdapter: FavoriteTVshowsAdapter
    private lateinit var favoriteTVshowsLayoutMgr: LinearLayoutManager

    private var favoriteTVshowsPage = 1

    //-----------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // that stuff ^^^ comes with/when you make project

        //-------------------------------------------------------------
        favoriteTVshows = findViewById(R.id.favorite_TVshow_pictures)

        // 'Pagination' section, step #7
        favoriteTVshowsLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        favoriteFavoriteTVshowsAdapter = FavoriteTVshowsAdapter(mutableListOf())
                                                { TVshow->showTVshowDetails(TVshow) }
        favoriteTVshows.adapter = favoriteFavoriteTVshowsAdapter
        favoriteTVshows.layoutManager = favoriteTVshowsLayoutMgr


        // call this (tutorial step #7(wait -- what step #7???)
        // as of step #7 of 'Pagination' -- do we still need this??
        TVshowsRepository.getFavoriteTVshows(
            favoriteTVshowsPage,
            onSuccess = ::onFavoriteTVshowsFetched,
            onError = ::onError
        )
        //-------------------------------------------------------------
        // Feature Two!  (Part One)
        TVwatchList = findViewById(R.id.watchlist_TVshow_pictures)
        TVwatchListLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        TVwatchList.layoutManager = TVwatchListLayoutMgr
        TVwatchListAdapter = FavoriteTVshowsAdapter(mutableListOf())
                                            { TVshow->showTVshowDetails(TVshow) }
        TVwatchList.adapter = TVwatchListAdapter

        //-------------------------------------------------------------
        // Feature Two, Part Two.....
        TVairingToday = findViewById(R.id.tv_airing_today_pictures)
        TVairingTodayLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        TVairingToday.layoutManager = TVairingTodayLayoutMgr
        TVairingTodayAdapter = FavoriteTVshowsAdapter(mutableListOf())
                                            { TVshow->showTVshowDetails(TVshow) }
        TVairingToday.adapter = TVairingTodayAdapter

        //-------------------------------------------------------------
        // 'Pagination' section step #5
        getFavoriteTVshows()
        getTVwatchList()
        getTVairingToday()
    }
    //-----------------------------------------------------------------------------------
    // from tutorial 'Callbacks' section step #3.....
    private fun onFavoriteTVshowsFetched(TVshows: List<TVshow>) {
        favoriteFavoriteTVshowsAdapter.appendTVshows(TVshows)
        attachFavoriteTVshowsOnScrollListener()

        //favoriteFavoriteTVshowsAdapter.appendTVshows(TVshows)
        //Log.d("MainActivity", "TVshows: $TVshows")
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_TVshows),
                                                            Toast.LENGTH_SHORT).show()
    }
    //-----------------------------------------------------------------------------------
    // from tutorial 'Pagination' section, step #4
    private fun getFavoriteTVshows() {
        TVshowsRepository.getFavoriteTVshows(
            favoriteTVshowsPage,
            ::onFavoriteTVshowsFetched,
            ::onError
        )
    }
    //-----------------------------------------------------------------------------------
    // tutorial 'Pagination' section, step #6
    private fun attachFavoriteTVshowsOnScrollListener() {
        favoriteTVshows.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = favoriteTVshowsLayoutMgr.itemCount
                val visibleItemCount = favoriteTVshowsLayoutMgr.childCount
                val firstVisibleItem = favoriteTVshowsLayoutMgr.findFirstVisibleItemPosition()

                if(firstVisibleItem + visibleItemCount >= totalItemCount/2) {
                    favoriteTVshows.removeOnScrollListener(this)
                    favoriteTVshowsPage++
                    getFavoriteTVshows()
                }
            }
        })
    }
    //===================================================================================
    // Feature Two!  (Part One)
    private lateinit var TVwatchList: RecyclerView
    private lateinit var TVwatchListAdapter: FavoriteTVshowsAdapter
    // well, crap -- i didn't need to rename the Adapter to have "Favorite".....
    private lateinit var TVwatchListLayoutMgr: LinearLayoutManager

    private var TVwatchListPage = 1
    //-----------------------------------------------------------------------------------
    private fun getTVwatchList(){
        TVshowsRepository.getWatchListTV(
            TVwatchListPage,
            ::onTVwatchListFetched,
            ::onError
        )
    }
    //-----------------------------------------------------------------------------------
    private fun attachTVwatchListOnScrollListener(){
        TVwatchList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = TVwatchListLayoutMgr.itemCount
                val visibleItemCount = TVwatchListLayoutMgr.childCount
                val firstVisibleItem = TVwatchListLayoutMgr.findFirstVisibleItemPosition()

                if(firstVisibleItem + visibleItemCount >= totalItemCount/2) {
                    TVwatchList.removeOnScrollListener(this)
                    TVwatchListPage++
                    getTVwatchList()
                }
            }
        })
    }
    //-----------------------------------------------------------------------------------
    private fun onTVwatchListFetched(TVshows: List<TVshow>) {
        TVwatchListAdapter.appendTVshows(TVshows)
        attachTVwatchListOnScrollListener()
    }
    //===================================================================================
    // Feature Two, con't..... (Part Two)
    private lateinit var TVairingToday: RecyclerView
    private lateinit var TVairingTodayAdapter: FavoriteTVshowsAdapter
    private lateinit var TVairingTodayLayoutMgr: LinearLayoutManager

    private var TVairingTodayPage = 1

    private fun getTVairingToday() {
        TVshowsRepository.getAiringToday(
            TVairingTodayPage,
            ::onTVairingTodayFetched,
            ::onError
        )
    }
    //-----------------------------------------------------------------------------------
    private fun attachTVairingTodayOnScrollListener() {
        TVairingToday.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = TVairingTodayLayoutMgr.itemCount
                val visibleItemCount = TVairingTodayLayoutMgr.childCount
                val firstVisibleItem = TVairingTodayLayoutMgr.findFirstVisibleItemPosition()

                if(firstVisibleItem + visibleItemCount >= totalItemCount/2) {
                    TVairingToday.removeOnScrollListener(this)
                    TVairingTodayPage++
                    getTVairingToday()
                }
            }
        })
    }
    //-----------------------------------------------------------------------------------
    private fun onTVairingTodayFetched(TVshows: List<TVshow>) {
        TVairingTodayAdapter.appendTVshows(TVshows)
        attachTVairingTodayOnScrollListener()
    }
    //===================================================================================
    // Feature Three! -- get the deets!
    private fun showTVshowDetails(TVshow: TVshow) {
        val intent = Intent(this, TVshowDetailsActivity::class.java)

        intent.putExtra(TVSHOW_BACKDROP, TVshow.backdropPath)
        intent.putExtra(TVSHOW_POSTER, TVshow.posterPath)
        intent.putExtra(TVSHOW_TITLE, TVshow.title)
        intent.putExtra(TVSHOW_RATING, TVshow.rating)
        intent.putExtra(TVSHOW_RELEASE_DATE, TVshow.releaseDate)
        intent.putExtra(TVSHOW_OVERVIEW, TVshow.overview)

        startActivity(intent)
    }
}
//=======================================================================================
// from tutorial, 'Callbacks' section, w/step #3:
// the `::` operator is used to create a class or function reference.
//      (see alternative syntax in tutorial)
//
// but using the `::` operator approach just makes things easier & cleaner.
// however, i leave it to your preference, which approach you want.
//      (may as well try out this Kotlin way!   [=   )
//
//--------------------------------------------------------------------------------------
// from tutorial, 'Create Horizontal Layout & Load Images Using Glide' section w/Step #4
// to make a horizontal list in `RecyclerView`, just provide the `LinearLayoutManager`
// with an orientation and a boolean flag that reverses the list or not.
//      [lines 23-27 up there]
//
// we removed the `log` in `onFavoriteTVshowsFetched()` and replaced it
// by updating the TV shows inside `FavoriteTVshowsAdapter`
//      [lines 41-46]
//
//---------------------------------------------------------------------------------------
// from tutorial, 'Pagination' section, end of Step #6
// let's go over the code bit-by-bit... the first three variables are:
//  `totalItemCount` -- the total number of TVshows inside our FavoriteTVshowsAdapter
//    this will keep increasing the more we call `favoriteTVshowsAdapter.appendTVshows()`
//
//  `visibleItemCount` -- the current number of child views attached to the RecyclerView
//   that are currently being recycled over and over again.
//   the value of this variable for common screen sizes will range roughly around 4-5,
//   which are 3 visible views, +1 left view that's not seen yet and +1 right view that's
//   not seen yet also.
//   the value will be higher if you have a bigger screen!
//
//  `firstVisibleItem` -- is the position of the left-most visible item in our list.
//
// the condition will be true if the user has scrolled past halfway, plus a buffered
//    value of `visibleItemCount`
//    if(firstVisibleItem + visibleItemCount >= totalItemCount/2) {
//          ............
//    }
//    after the condition is met, we disable the scroll listener, since we only want
//    this code to run once.
//    next, we increment favoriteTVshowsPage, and then call `getFavoriteTVshows()`
//    if(firstVisibleItem + visibleItemCount >= totalItemCount/2) {
//          favoriteTVshows.removeOnScrollListener(this)
//          favoriteTVshowsPage++
//          getFavoriteTVshows()
//    }
//---------------------------------------------------------------------------------------