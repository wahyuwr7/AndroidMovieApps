package com.wrseven.movieapps.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.utils.Construct.MV_TITLE
import com.wrseven.movieapps.utils.Construct.TV_TITLE
import com.wrseven.movieapps.utils.DummyData
import com.wrseven.movieapps.utils.DummyData.getDetailMovieEntity
import com.wrseven.movieapps.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DummyData.getOnlineDetailMovie()
    private val dummyMovieId = dummyMovie.id
    private val dummyTvShow = DummyData.getDetailTvEntity()
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var listRepository: ListRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(listRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(getDetailMovieEntity())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(listRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setItem(dummyMovieId, MV_TITLE)
        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(getDetailMovieEntity())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(listRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setItem(dummyMovieId, MV_TITLE)
        viewModel.setFavoriteMovie()
        verify(listRepository)
            .setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(DummyData.getDetailTvEntity())
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(listRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setItem(dummyTvShowId, TV_TITLE)
        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DummyData.getDetailTvEntity())
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(listRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setItem(dummyTvShowId, TV_TITLE)
        viewModel.setFavoriteTvShow()
        verify(listRepository)
            .setFavoriteTvShow(tvShow.value!!.data as TvEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}