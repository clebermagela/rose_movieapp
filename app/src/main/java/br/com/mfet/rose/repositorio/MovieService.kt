package br.com.mfet.rose.repositorio

import br.com.mfet.rose.models.Movie
import br.com.mfet.rose.repositorio.Api.API_KEY
import br.com.mfet.rose.repositorio.Api.API_KEY_QUERY
import br.com.mfet.rose.repositorio.Api.DEFAULT_LANGUAGE
import br.com.mfet.rose.repositorio.Api.LANGUAGE_QUERY
import br.com.mfet.rose.repositorio.Api.PAGE_QUERY
import br.com.mfet.rose.repositorio.Api.PATH_MOVIE_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("popular")
    fun listPopular(
        @Query(API_KEY_QUERY) apiKey: String = API_KEY,
        @Query(LANGUAGE_QUERY) idiom: String = DEFAULT_LANGUAGE,
        @Query(PAGE_QUERY) page: Int = 1
    ): Call<PageList>


    @GET("{$PATH_MOVIE_ID}")
    fun getMovieById(
        @Path(PATH_MOVIE_ID) id: Int,
        @Query(API_KEY_QUERY) apiKey: String = API_KEY,
        @Query(LANGUAGE_QUERY) idiom: String = DEFAULT_LANGUAGE
    ): Call<Movie>

}

class PageList(val results: List<Movie>)