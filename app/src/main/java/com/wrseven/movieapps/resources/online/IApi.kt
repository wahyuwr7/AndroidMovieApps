package com.wrseven.movieapps.resources.online

import com.wrseven.movieapps.resources.online.response.MovieDetailResponse
import com.wrseven.movieapps.resources.online.response.MovieResponse
import com.wrseven.movieapps.resources.online.response.TvDetailResponse
import com.wrseven.movieapps.resources.online.response.TvResponse
import com.wrseven.movieapps.utils.Construct.HEADER
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IApi {
    @GET("/3/movie/popular")
    @Headers(HEADER)
    fun getListPopularMovie(
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("/3/movie/{movie_id}")
    @Headers(HEADER)
    fun getDetailMovie(
        @Path("movie_id") movie_id: Int,
    ): Call<MovieDetailResponse>

    @GET("/3/tv/popular")
    @Headers(HEADER)
    fun getListPopularTv(
        @Query("page") page: Int
    ): Call<TvResponse>

    @GET("/3/tv/{tv_id}")
    @Headers(HEADER)
    fun getDetailTvShow(
        @Path("tv_id") tv_id: Int,
    ): Call<TvDetailResponse>
}