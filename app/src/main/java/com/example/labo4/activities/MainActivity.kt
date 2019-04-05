package com.example.labo4.activities

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.labo4.R
import com.example.labo4.adapters.MovieAdapter
import com.example.labo4.network.NetworkUtils
import com.example.labo4.pojos.Movie
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var movieList: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    fun initRecyclerView(){
        viewManager = LinearLayoutManager(this)

        movieAdapter = MovieAdapter(movieList,{movieItem: Movie -> movieItemClicked(movieItem)})

        movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = movieAdapter
        }
    }

    fun initSearchButton() = add_movie_btn.setOnClickListener{
        if(!movie_name_et.text.toString().isEmpty()){
            FetchMovie().execute(movie_name_et.text.toString())
        }
    }

    private inner class FetchMovie:AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String?): String {
            if(params.isNullOrEmpty()) return ""

            val movieName = params[0]

            val movieUrl = NetworkUtils().buildtSearchUrl(movieName)

            return try {
                NetworkUtils().getResponseFromHttpUrl(movieUrl)
            } catch (e: IOException){
                ""
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }


}
