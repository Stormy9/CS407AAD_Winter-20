package com.example.stormyapiapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop


// from tutorial section 'Create Horizontal List & Load Images Using Glide' Step #3

class FavoriteTVshowsAdapter(

    private var TVshows: MutableList<TVshow>,
    private val onTVshowClick: (TVshow: TVshow)->Unit
): RecyclerView.Adapter<FavoriteTVshowsAdapter.TVshowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): TVshowViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tv_show, parent, false)
        return TVshowViewHolder(view)
    }
    //-----------------------------------------------------------------------------------

    override fun getItemCount(): Int=TVshows.size

    override fun onBindViewHolder(holder: TVshowViewHolder, position: Int) {
        holder.bind(TVshows[position])
    }
    //===================================================================================

    fun appendTVshows(TVshows: List<TVshow>) {
        this.TVshows.addAll(TVshows)
        notifyItemRangeInserted(
            this.TVshows.size,
            TVshows.size-1
        )
        //notifyDataSetChanged()
    }
    //===================================================================================

    inner class TVshowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvPoster: ImageView = itemView.findViewById(R.id.item_tv_poster)

                fun bind(TVshows: TVshow) {
                    Glide.with(itemView)
                        .load("https://image.tmdb.org/t/p/w342${TVshows.posterPath}")
                        .transform(CenterCrop())
                        .into(tvPoster)

                    // to get the details of the TV show tapped/clicked.....
                    itemView.setOnClickListener { onTVshowClick.invoke(TVshows) }
                }
    }
}
//=======================================================================================
// from tutorial -- 'Create a Horizontal List and Load Images Using Glide' step #3:
// `.load("https://image.tmdb.org/t/p/w342/<poster_url>")`
//      is how you fetch a poster of a movie from TMDB.
//      you can learn more about fetching images from TMDB here (linked).
//
//  available poster sizes are (not applicable here since i'm doing TV pictures?)
//      you can go for 'original' if you want the highest image quality but it'll take
//      time to load... a size of w342 should be enough for most screens.
//
// from tutorial -- 'Pagination' section step #1:
// we changed the type of the `TVshows` variable to `MutableList`, because we now have
// a dynamic list of TVshows.
//
// instead of using `notifyDataSetChanged()`, we use `notifyItemRangeInserted()`,
// because we don't want to refresh the whole list -- we just want to notify that
// there are new items added from this 'start' and 'end' positions.