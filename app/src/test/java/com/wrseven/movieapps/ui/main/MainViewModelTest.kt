package com.wrseven.movieapps.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.ui.main.movie.MovieViewModel
import com.wrseven.movieapps.ui.main.tv.TvShowViewModel
import com.wrseven.movieapps.utils.SortUtils.DEFAULTS
import com.wrseven.movieapps.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var tvViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var listRepository: ListRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvPagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(listRepository)
        tvViewModel = TvShowViewModel(listRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(moviePagedList)
        `when`(dummyMovies.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(listRepository.getMovies(DEFAULTS)).thenReturn(movies)
        val movie = movieViewModel.getMovies(DEFAULTS).value?.data
        verify(listRepository).getMovies(DEFAULTS)
        assertNotNull(movie)
        assertEquals(10, movie?.size)

        movieViewModel.getMovies(DEFAULTS).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(tvPagedList)
        `when`(dummyTvShow.data?.size).thenReturn(10)
        val tvShows = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvShows.value = dummyTvShow

        `when`(listRepository.getTvShows(DEFAULTS)).thenReturn(tvShows)
        val tvShow = tvViewModel.getTvShows(DEFAULTS).value?.data
        verify(listRepository).getTvShows(DEFAULTS)
        assertNotNull(tvShow)
        assertEquals(10, tvShow?.size)

        tvViewModel.getTvShows(DEFAULTS).observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTvShow)
    }
}