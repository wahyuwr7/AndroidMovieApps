package com.wrseven.movieapps.ui.main.movie

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
import com.wrseven.movieapps.databinding.FragmentMovieBinding
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.ui.ViewModelFactory
import com.wrseven.movieapps.ui.detail.DetailActivity
import com.wrseven.movieapps.utils.Construct
import com.wrseven.movieapps.utils.Construct.TOTAL_GRID
import com.wrseven.movieapps.utils.Construct.sort
import com.wrseven.movieapps.utils.SortUtils.DEFAULTS
import com.wrseven.movieapps.utils.SortUtils.NEWEST
import com.wrseven.movieapps.utils.SortUtils.OLDEST
import com.wrseven.movieapps.utils.SortUtils.RATING
import com.wrseven.movieapps.vo.Resource
import com.wrseven.movieapps.vo.Status.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        TOTAL_GRID =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
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

    private fun showItems() {
        showProgressBar(true)
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        movieAdapter = MovieAdapter()
        viewModel.getMovies(DEFAULTS).observe(viewLifecycleOwner, movieObserver)

        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context, TOTAL_GRID)
            setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                LOADING -> showProgressBar(true)
                SUCCESS -> {
                    showProgressBar(false)
                    movieAdapter.submitList(movies.data)
                    movieAdapter.setOnItemClickCallback(this)
                    movieAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ITEM, id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, Construct.MV_TITLE)
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
            R.id.sort_newest -> sort = NEWEST
            R.id.sort_oldest -> sort = OLDEST
        }

        viewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressCircular.isVisible = state
        binding.rvMovie.isInvisible = state
    }
}