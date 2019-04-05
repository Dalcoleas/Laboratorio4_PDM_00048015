package com.example.labo4.network

import android.net.Uri
import java.net.MalformedURLException
import java.net.URL

class NetworkUtils {

    val MOVIES_API_BASEURL = "http://www.omdbapi.com/"
    val TOKEN_API = "d007b662"

    fun buildtSearchUrl (movieName : String) :URL{

        val builtUri = Uri.parse(MOVIES_API_BASEURL).buildUpon().appendQueryParameter("apikey", TOKEN_API).appendQueryParameter("t", movieName).build()

        return try{
            URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            URL("algo")
        }
    }
}