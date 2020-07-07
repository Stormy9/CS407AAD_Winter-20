package com.example.stormyapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

// so it seems that these go here.....   [=
const val TVSHOW_BACKDROP = "extra_TVshow_backdrop"
const val TVSHOW_POSTER = "extra_TVshow_poster"
const val TVSHOW_TITLE = "extra_TVshow_title"
const val TVSHOW_RATING = "extra_TVshow_rating"
const val TVSHOW_RELEASE_DATE = "extra_TVshow_release_date"
const val TVSHOW_OVERVIEW = "extra_TVshow_overview"

// these ^^^ variables (aren't they constants?) will be used as keys
// when we pass intent extras to TVshowDetailsActivity.....


class TVshowDetailsActivity : AppCompatActivity() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_details)
        // this ^^^ comes with

        //-------------------------------------------------------------------------------
        backdrop = findViewById(R.id.tvShow_backdrop)
        poster = findViewById(R.id.tvShow_poster)
        title = findViewById(R.id.tvShow_title)
        rating = findViewById(R.id.tvShow_rating)
        releaseDate = findViewById(R.id.tvShow_release_date)
        overview = findViewById(R.id.tvShow_overview)

        //-------------------------------------------------------------------------------
        val extras = intent.extras

        if(extras != null) {
            populateTVshowDetails(extras)
        } else {
            finish()
        }
        //-------------------------------------------------------------------------------
    }
    private fun populateTVshowDetails(extras: Bundle) {
        extras.getString(TVSHOW_BACKDROP)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/woriginal$backdropPath")
                .transform(CenterCrop())
                .into(poster)
        }
        extras.getString(TVSHOW_POSTER)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .into(poster)
        }
        title.text = extras.getString(TVSHOW_TITLE, "")
        rating.rating = extras.getFloat(TVSHOW_RATING, 0f)/2
        releaseDate.text = extras.getString(TVSHOW_RELEASE_DATE, "")
        overview.text = extras.getString(TVSHOW_OVERVIEW,"")
    }
    //-----------------------------------------------------------------------------------
}
//=======================================================================================