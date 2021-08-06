package br.com.mfet.rose.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.mfet.rose.databinding.ActivityMainBinding
import br.com.mfet.rose.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterFilmes: MovieViewAdapter

    private lateinit var viewModel: MainActivityViewModel

    companion object {
        const val MOVIE_ID = "br.com.mfet.rose.view.MainActivity.MOVIE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)
            .get(MainActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        adapterFilmes = MovieViewAdapter {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID, it)
            startActivity(intent)
        }
        binding.recycleView1.adapter = adapterFilmes

        viewModel.getObserver().observe(this, Observer {
            if (it != null)
                adapterFilmes.addItems(it)
        })

        viewModel.movieListApiCall()
    }
}