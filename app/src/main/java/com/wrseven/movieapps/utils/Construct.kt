package com.wrseven.movieapps.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wrseven.movieapps.BuildConfig
import com.wrseven.movieapps.utils.SortUtils.DEFAULTS

object Construct {
    const val MV_TITLE = "Detail Movie"
    const val TV_TITLE = "Detail TV Show"

    var TOTAL_GRID = 0

    fun setGlideImage(context: Context, path: String, imageView: ImageView, drawable: Int) {
        Glide.with(context)
            .load(path)
            .placeholder(drawable)
            .error(drawable)
            .into(imageView)
    }

    var sort = DEFAULTS

    const val BASE_URL = "https://api.themoviedb.org/"
    const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"
    const val HEADER = "Authorization: Bearer ${BuildConfig.apiToken}"
}