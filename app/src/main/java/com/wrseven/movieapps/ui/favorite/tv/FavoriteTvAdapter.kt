package com.wrseven.movieapps.ui.favorite.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wrseven.movieapps.R
import com.wrseven.movieapps.databinding.FavItemRowBinding
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.utils.Construct.BASE_IMG_URL
import com.wrseven.movieapps.utils.Construct.setGlideImage

class FavoriteTvAdapter : PagedListAdapter<TvEntity, FavoriteTvAdapter.TvShowViewHolder>(
    DIFF_CALLBACK
) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getSwipedData(swipedPosition: Int): TvEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val favItemRowBinding =
            FavItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(favItemRowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: FavItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvEntity) {
            with(binding) {
                tvTitle.text = tv.name
                tvDesc.text = tv.overview

                setGlideImage(
                    itemView.context,
                    BASE_IMG_URL + tv.poster,
                    ivFavPoster,
                    R.drawable.placeholder
                )

                itemView.setOnClickListener { onItemClickCallback.onItemClicked(tv.id.toString()) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}