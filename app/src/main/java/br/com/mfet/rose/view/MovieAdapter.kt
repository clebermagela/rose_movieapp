package br.com.mfet.rose.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mfet.rose.databinding.MovieItemBinding
import br.com.mfet.rose.models.Movie
import com.bumptech.glide.Glide

class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

class MovieViewAdapter(
    val movieClickListener: (Int) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    val movieList : MutableList<Movie> = mutableListOf()

    fun addItems(items: List<Movie>) {
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movieList[position]
//        val itemUpComing = moviesUpComing[position]

        holder.let {

            it.binding.title = item.title

            Glide.with(it.binding.root)
                .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
                .into(it.binding.ivPoster)
            it.binding.itemBackground.setOnClickListener {
                movieClickListener(item.id)
            }
        }
    }

    override fun getItemCount() = movieList.size

}