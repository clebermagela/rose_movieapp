package br.com.mfet.rose.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mfet.rose.models.Movie
import br.com.mfet.rose.repositorio.MovieInstance
import br.com.mfet.rose.repositorio.PageList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    private val movieLiveDataPopular : MutableLiveData<List<Movie>?> = MutableLiveData()
    private val movieLiveDataUpComing : MutableLiveData<List<Movie>?> = MutableLiveData()


    fun getPopularObserver(): MutableLiveData<List<Movie>?> {
        return movieLiveDataPopular
    }

    fun getObserver(): MutableLiveData<List<Movie>?> {
        return movieLiveDataUpComing
    }

    fun movieListApiCall() {
        viewModelScope.launch(Dispatchers.IO) {

            val callPopular = MovieInstance.apiMovie.listPopular()
            callPopular.enqueue(object : Callback<PageList> {
                override fun onResponse(call: Call<PageList>, response: Response<PageList>) {

                    movieLiveDataPopular.postValue(response.body()?.results)
                }

                override fun onFailure(call: Call<PageList>, t: Throwable) {
                    Log.d("TAG", "Erro")
                }
            })
        }
    }
}