package com.wrseven.movieapps.ui.favorite.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.wrseven.movieapps.R
import com.wrseven.movieapps.databinding.FragmentFavoriteTvBinding
import com.wrseven.movieapps.ui.ViewModelFactory
import com.wrseven.movieapps.ui.detail.DetailActivity
import com.wrseven.movieapps.utils.Construct.TV_TITLE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteTvFragment : Fragment(), FavoriteTvAdapter.OnItemClickCallback {

    private var _binding: FragmentFavoriteTvBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: FavoriteTvViewModel
    private lateinit var adapter: FavoriteTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTvBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavTvShows().observe(viewLifecycleOwner, { favTvShow ->
            if (favTvShow != null) {
                adapter.submitList(favTvShow)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvFavTv)

        if (activity != null) {
            showItems()
            refreshListener()
        }
    }

    private fun refreshListener() {
        binding?.apply {
            refresh.setOnRefreshListener {
                showItems()
                GlobalScope.launch {
                    delay(1000)
                    refresh.isRefreshing = false
                }
            }
        }
    }

    private fun showItems() {
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]

        adapter = FavoriteTvAdapter()
        adapter.setOnItemClickCallback(this)

        viewModel.getFavTvShows().observe(viewLifecycleOwner, { favTvShow ->
            if (favTvShow != null) {
                adapter.submitList(favTvShow)
            }
        })

        with(binding?.rvFavTv) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = adapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvShowEntity = adapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavTvShow(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    tvShowEntity?.let { viewModel.setFavTvShow(it) }
                    Toast.makeText(requireContext(), "Undo Successful", Toast.LENGTH_SHORT)
                        .show()
                }
                snackBar.show()
                Toast.makeText(requireContext(), "Delete Successful", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    })

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ITEM, id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TV_TITLE)
        context?.startActivity(intent)
    }
}