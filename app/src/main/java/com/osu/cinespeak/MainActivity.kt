package com.osu.cinespeak

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import com.google.android.material.button.MaterialButton
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity: AppCompatActivity(){

    private val genreIdToNameMap = mutableMapOf<Int, String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set default view
        setContentView(R.layout.activity_main)

        // initialize the filter parameters
        // Add methods here

        // access to the searchbar
        val searchBar: EditText = findViewById(R.id.search_title)

        // access the spinner elements themselves
        val spokeSpin: Spinner  = findViewById(R.id.spoken_dd)
        val genreSpin: Spinner = findViewById(R.id.genre_dd)

        // placeholder arrays for spinner population (REPLACE THIS WITH
        // ACTUAL LISTS FROM TMDB)
        val spokeOptions = R.array.lang_array
        val genreOptions = R.array.genre_array

        // set the entries for the spinners
        populateSpinner(spokeSpin, spokeOptions)
        populateSpinner(genreSpin, genreOptions)

        // set up variables for spoken language and genre
        var selectLang = "Any";
        var selectGenre = "Any";
        var searchQuery = "";

        /* create mutable list of maps (key-value dictionary pairs) to grab appropriate fields for
        recyclerview adapter (PLACEHOLDER)*/
        var arrayOfMovies = arrayOf(
            mapOf("poster_path" to "https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_540.jpg",
                "title" to "The Super Mario Bros. Movie",
                "genre" to "Action, Adventure",
                "runtime" to "92"),
            mapOf("poster_path" to "https://images.dog.ceo/breeds/hound-ibizan/n02091244_595.jpg",
                "title" to "The Super Mario Bros. Movie",
                "genre" to "Action, Adventure",
                "runtime" to "92")
        )
        // Call the functions to fetch genre and language data
        getMovieGenres()
        getConfigurationLanguages()

        // get search button
        val button: MaterialButton = findViewById(R.id.search_button)
        button.visibility = View.GONE

        // set up recyclerview
        val recyclerView: RecyclerView = findViewById(R.id.movieRecyclerView)
        recyclerView.visibility = View.GONE

        // set spinner listeners
        spokeSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                val itemSelect = spokeSpin.getItemAtPosition(p2) as String
                // CHANGE THIS TO DEFAULT OPTION
                if (itemSelect == "Any"){
                    selectLang = itemSelect
                    button.visibility = View.GONE
                } else {
                    button.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                if (selectLang == "Any"){
                    button.visibility = View.GONE
                }
            }
        }

        genreSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                val itemSelect = genreSpin.getItemAtPosition(p2) as String
                // CHANGE THIS TO DEFAULT OPTION
                if (itemSelect == "Any"){
                    selectGenre = itemSelect
                    button.visibility = View.GONE
                } else {
                    button.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                button.visibility = View.GONE
            }
        }

        /*
        Set up search field edit and grab keyword(s) upon completion, then closes keyboard
        */
        searchBar.setOnEditorActionListener{ _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(searchBar.windowToken, 0)
                searchQuery = searchBar.text.toString()

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        /* Set click action for search (right now set to making toasts,
        will need API team to hook up the keyword, language, genre query request*/
        button.setOnClickListener{
            var toastText = "Spoken: $selectLang, Genre: $selectGenre, Keyword(s)$searchQuery"
            Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()

            // Make the API request with the selected parameters
            makeApiRequest(selectLang, selectGenre, searchQuery)

            // populates the recyclerview (will need to fix this approach if possible)
            val recycleAdapter = MovieAdapter(arrayOfMovies)
            recyclerView.adapter = recycleAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.visibility = View.VISIBLE
        }

        // Create methods to grab movie data (TODO API/backend team)
        /* CHANGE THE ARRAY arrayOfMovies TO FIT YOUR PARSED DATA*/

    }
    private fun getMovieGenres() {
        val client = AsyncHttpClient()
        val apiUrl = "https://api.themoviedb.org/3/genre/movie/list"
        val params = RequestParams()
        params["api_key"] = "d027ce23bad3a259cffc5f57a3fbeb09"

        client.get(apiUrl, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, responseBody: String?) {

                if (!responseBody.isNullOrBlank()) {
                    // Parse the JSON response for movie genres
                    val genres = parseMovieGenres(JSONObject(responseBody))
                    // Handle the parsed genres
                    Log.d("getMovieGenres", "Movie genres: $genres")
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                responseString: String?,
                throwable: Throwable?
            ) {
                // Handle the failure
                Log.e("getMovieGenres", "Failed to fetch movie genres. Status code: $statusCode")
            }
        })
    }

    private fun parseMovieGenres(response: JSONObject): List<Map<String, Any>> {
        val genresList = mutableListOf<Map<String, Any>>()

        // Check if the response contains the "genres" array
        if (response.has("genres")) {
            val genresArray = response.getJSONArray("genres")
            for (i in 0 until genresArray.length()) {
                val genreObject = genresArray.getJSONObject(i)
                val genreMap = mapOf(
                    "id" to genreObject.getInt("id"),
                    "name" to genreObject.getString("name")
                )
                genresList.add(genreMap)
            }
        }

        return genresList
    }

    private fun getConfigurationLanguages() {
        val client = AsyncHttpClient()
        val apiUrl = "https://api.themoviedb.org/3/configuration/languages"
        val params = RequestParams()
        params["api_key"] = "d027ce23bad3a259cffc5f57a3fbeb09"

        client.get(apiUrl, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, responseBody: String?) {
                // Handle the successful response here
                if (!responseBody.isNullOrBlank()) {
                    try {
                        // Parse the JSON response for configuration languages
                        val languages = parseConfigurationLanguages(JSONArray(responseBody))
                        // Handle the parsed languages
                        Log.d("getConfigurationLanguages", "Configuration languages: $languages")
                    } catch (e: JSONException) {
                        Log.e("getConfigurationLanguages", "Error parsing JSON array", e)
                    }

                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                responseString: String?,
                throwable: Throwable?
            ) {
                // Handle the failure
                Log.e("getConfigurationLanguages",
                    "Failed to fetch configuration languages. Status code: $statusCode")
            }
        })
    }

    private fun parseConfigurationLanguages(response: JSONArray): List<String> {
        val languagesList = mutableListOf<String>()

        for (i in 0 until response.length()) {
            val languageObject = response.getJSONObject(i)
            // Assuming each language object has a key like "name" for the language name
            val languageName = languageObject.optString("name", "")
            languagesList.add(languageName)
        }

        return languagesList
    }

    private fun makeApiRequest(language: String, genre: String?, query: String) {
        val client = AsyncHttpClient()
        val apiUrl = "https://api.themoviedb.org/3/search/movie"

        val params = RequestParams()
        params["language"] = language
        params["query"] = query
        genre?.let { params["with_genres"] = it }
        params["api_key"] = "d027ce23bad3a259cffc5f57a3fbeb09"

        client.get(apiUrl, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, responseBody: String?) {
                // Handle the successful response here
                if (!responseBody.isNullOrBlank()) {
                    // Parse the JSON response
                    val movies = parseMovies(JSONObject(responseBody))
                    updateUIWithMovies(movies)
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                responseString: String?,
                throwable: Throwable?
            ) {
                // Handle the failure
                Log.e("APIRequest", "Failed to fetch movies. Status code: $statusCode")
            }
        })
    }


    private fun parseMovies(response: JSONObject): List<Map<String, String>> {
        val moviesList = mutableListOf<Map<String, String>>()

        // Check if the response contains the "results" array
        if (response.has("results")) {
            val resultsArray = response.getJSONArray("results")
            for (i in 0 until resultsArray.length()) {
                val movieObject = resultsArray.getJSONObject(i)
                val genresArray = movieObject.getJSONArray("genre_ids")

                val genreList = mutableListOf<String>()
                for (j in 0 until genresArray.length()) {
                    val genreId = genresArray.getInt(j)
                    // Assuming you have a function to get the genre name based on genreId
                    val genreName = genreIdToNameMap[genreId] ?: ""
                    if (genreName.isNotEmpty()) {
                        genreList.add(genreName)
                    }
                }
                val movieMap = mapOf(
                    "poster_path" to movieObject.getString("poster_path"),
                    "title" to movieObject.getString("title"),
                    "genre" to genreList.joinToString(", "),
                    "runtime" to movieObject.getString("runtime")
                )
                moviesList.add(movieMap)
            }
        }

        return moviesList
    }

    private fun updateUIWithMovies(movies: List<Map<String, String>>) {
        // Assuming you have a RecyclerView with its adapter already set up
        val recyclerView: RecyclerView = findViewById(R.id.movieRecyclerView)

        // Create an adapter with the list of movies
        val movieAdapter = MovieAdapter(movies.toTypedArray())


        // Set the adapter to the RecyclerView
        recyclerView.adapter = movieAdapter
    }

    // set adapters for spinners
    private fun populateSpinner(
        item: Spinner,
        array: Int
    ){
        // populate the dropdowns/spinners
        // see https://developer.android.com/develop/ui/views/components/spinner
        ArrayAdapter.createFromResource(
            this,
            array,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            item.adapter = adapter
        }
    }

}