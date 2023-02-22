package com.wrseven.movieapps.ui.main.tv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wrseven.movieapps.R
import com.wrseven.movieapps.databinding.ItemRowBinding
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.utils.Construct.BASE_IMG_URL
import com.wrseven.movieapps.utils.Construct.setGlideImage

class TvShowAdapter :
    PagedListAdapter<TvEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

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

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemRowBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(tv: TvEntity) {
            with(binding) {
                val year = tv.year.subSequence(0, 4)
                tvTitle.text = "${tv.name} (${year})"
                tvDesc.text = tv.overview
                tvRating.text = tv.rating.toString()

                setGlideImage(
                    itemView.context,
                    BASE_IMG_URL + tv.poster,
                    imgItem,
                    R.drawable.placeholder
                )

                itemView.setOnClickListener { onItemClickCallback.onItemClicked(tv.id.toString()) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}