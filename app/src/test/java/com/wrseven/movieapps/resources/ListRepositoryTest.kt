package com.wrseven.movieapps.resources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.wrseven.movieapps.resources.local.LocalDataSource
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.resources.online.RemoteDataSource
import com.wrseven.movieapps.utils.AppExecutors
import com.wrseven.movieapps.utils.DummyData
import com.wrseven.movieapps.utils.DummyData.getDetailMovieEntity
import com.wrseven.movieapps.utils.DummyData.getDetailTvEntity
import com.wrseven.movieapps.utils.LiveDataTestUtility
import com.wrseven.movieapps.utils.PagedListUtil
import com.wrseven.movieapps.utils.SortUtils.DEFAULTS
import com.wrseven.movieapps.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
class ListRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val dummyListRepository = DummyListRepository(remote, local, appExecutors)

    private val movieResponse = DummyData.getDummyOnlineMovie()
    private val movieId = movieResponse[0].id
    private val movieDetail = DummyData.getOnlineDetailMovie()

    private val tvShowResponse = DummyData.getDummyOnlineTv()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetail = DummyData.getOnlineDetailTv()


    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies(DEFAULTS)).thenReturn(dataSourceFactory)
        dummyListRepository.getMovies(DEFAULTS)

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyOnlineMovie()))
        verify(local).getAllMovies(DEFAULTS)
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovies() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = getDetailMovieEntity()
        `when`(local.getMovieById(movieId.toString()))
            .thenReturn(dummyDetail)

        val movieDetailEntity =
            LiveDataTestUtility.getValue(dummyListRepository.getDetailMovie(movieId))
        verify(local).getMovieById(movieId.toString())
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        dummyListRepository.getFavoriteMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyOnlineMovie()))
        verify(local).getFavMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        dummyListRepository.setFavoriteMovie(getDetailMovieEntity(), true)
        verify(local).setFavoriteMovie(getDetailMovieEntity(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getAllTvShows(DEFAULTS)).thenReturn(dataSourceFactory)
        dummyListRepository.getTvShows(DEFAULTS)

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyOnlineTv()))
        verify(local).getAllTvShows(DEFAULTS)
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvEntity>()
        dummyDetail.value = getDetailTvEntity()
        `when`(local.getTvShowById(tvShowId.toString())).thenReturn(dummyDetail)

        val tvEntity =
            LiveDataTestUtility.getValue(dummyListRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId.toString())

        assertNotNull(tvEntity)
        assertEquals(tvShowDetail.id, tvEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getFavTvShows()).thenReturn(dataSourceFactory)
        dummyListRepository.getFavoriteTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyOnlineTv()))
        verify(local).getFavTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        dummyListRepository.setFavoriteTvShow(getDetailTvEntity(), true)
        verify(local).setFavoriteTvShow(getDetailTvEntity(), true)
        verifyNoMoreInteractions(local)
    }
}