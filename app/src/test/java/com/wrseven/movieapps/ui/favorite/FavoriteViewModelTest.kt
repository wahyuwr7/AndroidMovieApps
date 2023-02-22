package com.wrseven.movieapps.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.ui.favorite.movie.FavoriteMovieViewModel
import com.wrseven.movieapps.ui.favorite.tv.FavoriteTvViewModel
import com.wrseven.movieapps.utils.DummyData
import com.wrseven.movieapps.utils.DummyData.getDetailMovieEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var favMovieViewModel: FavoriteMovieViewModel
    private lateinit var favTvViewModel: FavoriteTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var listRepository: ListRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvObserver: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var tvPagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        favMovieViewModel = FavoriteMovieViewModel(listRepository)
        favTvViewModel = FavoriteTvViewModel(listRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(listRepository.getFavoriteMovies()).thenReturn(movies)
        val movie = favMovieViewModel.getFavMovies().value
        verify(listRepository).getFavoriteMovies()
        Assert.assertNotNull(movie)
        Assert.assertEquals(10, movie?.size)

        favMovieViewModel.getFavMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun setFavMovie() {
        favMovieViewModel.setFavMovie(getDetailMovieEntity())
        verify(listRepository).setFavoriteMovie(getDetailMovieEntity(), true)
        verifyNoMoreInteractions(listRepository)
    }

    @Test
    fun getFavTvShows() {
        val dummyTvShow = tvPagedList
        `when`(dummyTvShow.size).thenReturn(10)
        val tvShows = MutableLiveData<PagedList<TvEntity>>()
        tvShows.value = dummyTvShow

        `when`(listRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShow = favTvViewModel.getFavTvShows().value
        verify(listRepository).getFavoriteTvShows()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(10, tvShow?.size)

        favTvViewModel.getFavTvShows().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTvShow)
    }

    @Test
    fun setFavTv() {
        favTvViewModel.setFavTvShow(DummyData.getDetailTvEntity())
        verify(listRepository).setFavoriteTvShow(DummyData.getDetailTvEntity(), true)
        verifyNoMoreInteractions(listRepository)
    }
}