package com.wrseven.movieapps.ui.main.tv

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.wrseven.movieapps.R
import com.wrseven.movieapps.databinding.FragmentTvshowBinding
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.ui.ViewModelFactory
import com.wrseven.movieapps.ui.detail.DetailActivity
import com.wrseven.movieapps.utils.Construct.TOTAL_GRID
import com.wrseven.movieapps.utils.Construct.TV_TITLE
import com.wrseven.movieapps.utils.Construct.sort
import com.wrseven.movieapps.utils.SortUtils
import com.wrseven.movieapps.utils.SortUtils.DEFAULTS
import com.wrseven.movieapps.utils.SortUtils.RATING
import com.wrseven.movieapps.vo.Resource
import com.wrseven.movieapps.vo.Status.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TvFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentTvshowBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        TOTAL_GRID = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            3 else 2
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showItems()
            refreshListener()
        }
    }

    private fun showItems() {
        showProgressBar(true)
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        tvShowAdapter = TvShowAdapter()
        viewModel.getTvShows(DEFAULTS).observe(viewLifecycleOwner, { tvShows ->
            if (tvShows != null) {
                when (tvShows.status) {
                    LOADING -> showProgressBar(true)
                    SUCCESS -> {
                        showProgressBar(false)
                        tvShowAdapter.submitList(tvShows.data)
                        tvShowAdapter.setOnItemClickCallback(this)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvTv) {
            layoutManager = GridLayoutManager(context, TOTAL_GRID)
            setHasFixedSize(true)
            this.adapter = tvShowAdapter
        }
    }

    private fun refreshListener() {
        binding.apply {
            refresh.setOnRefreshListener {
                showItems()
                GlobalScope.launch {
                    delay(1000)
                    refresh.isRefreshing = false
                }
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ITEM, id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TV_TITLE)
        context?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.menu_mv, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_default -> sort = DEFAULTS
            R.id.sort_rating -> sort = RATING
            R.id.sort_newest -> sort = SortUtils.NEWEST
            R.id.sort_oldest -> sort = SortUtils.OLDEST
        }
        viewModel.getTvShows(sort).observe(viewLifecycleOwner, tvObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    private val tvObserver = Observer<Resource<PagedList<TvEntity>>> { tv ->
        if (tv != null) {
            when (tv.status) {
                LOADING -> showProgressBar(true)
                SUCCESS -> {
                    showProgressBar(false)
                    tvShowAdapter.submitList(tv.data)
                    tvShowAdapter.setOnItemClickCallback(this)
                    tvShowAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressCircular.isVisible = state
        binding.rvTv.isInvisible = state
    }
}