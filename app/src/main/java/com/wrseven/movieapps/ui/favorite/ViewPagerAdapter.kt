@file:Suppress("DEPRECATION")

package com.wrseven.movieapps.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wrseven.movieapps.R
import com.wrseven.movieapps.ui.favorite.movie.FavoriteMovieFragment
import com.wrseven.movieapps.ui.favorite.tv.FavoriteTvFragment

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])
}