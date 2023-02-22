package com.wrseven.movieapps.resources.online

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wrseven.movieapps.resources.online.response.*
import com.wrseven.movieapps.utils.IdleResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getMovies(): LiveData<ApiResponse<List<Movie>>> {
        IdleResources.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        val client = RetroConfig.getService().getListPopularMovie(1)

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<Movie>)
                IdleResources.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                IdleResources.decrement()
            }
        })

        return resultMovies
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        IdleResources.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        val client = RetroConfig.getService().getDetailMovie(movieId)

        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                resultDetailMovie.value =
                    ApiResponse.success(response.body() as MovieDetailResponse)
                IdleResources.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
                IdleResources.decrement()
            }
        })

        return resultDetailMovie
    }

    fun getTvShows(): LiveData<ApiResponse<List<Tv>>> {
        IdleResources.increment()
        val resultTvs = MutableLiveData<ApiResponse<List<Tv>>>()
        val client = RetroConfig.getService().getListPopularTv(1)

        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                resultTvs.value = ApiResponse.success(response.body()?.results as List<Tv>)
                IdleResources.decrement()
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvs onFailure : ${t.message}")
                IdleResources.decrement()
            }
        })

        return resultTvs
    }

    fun getDetailTvShow(tvId: String): LiveData<ApiResponse<TvDetailResponse>> {
        IdleResources.increment()
        val resultDetailTv = MutableLiveData<ApiResponse<TvDetailResponse>>()
        val client = RetroConfig.getService().getDetailTvShow(tvId.toInt())

        client.enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                resultDetailTv.value = ApiResponse.success(response.body() as TvDetailResponse)
                IdleResources.decrement()
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTv onFailure : ${t.message}")
                IdleResources.decrement()
            }
        })

        return resultDetailTv
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}