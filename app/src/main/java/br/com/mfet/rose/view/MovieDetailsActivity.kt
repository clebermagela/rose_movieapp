package br.com.mfet.rose.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.mfet.rose.databinding.ActivityMovieDetailsBinding
import br.com.mfet.rose.models.Movie
import br.com.mfet.rose.view.MainActivity.Companion.MOVIE_ID
import br.com.mfet.rose.viewModel.MovieDetailsViewModel
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()

        viewModel = ViewModelProvider(this)
            .get(MovieDetailsViewModel::class.java)

        val movieId = intent.getIntExtra(MOVIE_ID, -1)

        viewModel.getMovie().observe(this, {
            if (it != null) {
                setDetails(it)
            } else {
                Toast.makeText(this, "Falhou", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        initViewModel(movieId)

    }

    fun setupBinding() {
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun initViewModel(id: Int) { viewModel.getApiMovieCall(id)}

    private fun setDetails(it: Movie) {
        binding.apply {
            Glide.with(root)
                .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                .into(poster)

            titleText.text = it.title

            tvOverview.text = it.overview
            genero.text = it.genres.toString()
        }

    }
}