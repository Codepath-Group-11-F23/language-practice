package com.osu.cinespeak

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
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
import com.google.android.material.imageview.ShapeableImageView
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


class MainActivity: AppCompatActivity(){

    lateinit var genres: HashMap<String, Int>
    lateinit var languages: HashMap<String, String>
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val searchBar: EditText = findViewById(R.id.search_title)

        val spokeSpin: Spinner  = findViewById(R.id.spoken_dd)
        val genreSpin: Spinner = findViewById(R.id.genre_dd)

        getMovieGenres()
        getConfigurationLanguages()

        var selectLang = ""
        var selectGenre = ""
        var selectTitle = ""

        /* create mutable list of maps (key-value dictionary pairs) to grab appropriate fields for
        recyclerview adapter (PLACEHOLDER)*/
        val arrayOfMovies: Array<Map<String, Any>> = emptyArray()

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
                if (itemSelect == "No Language"){
                    button.visibility = View.GONE
                } else {
                    selectLang = itemSelect
                    button.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                if (selectLang == ""){
                    button.visibility = View.GONE
                }
            }
        }

        genreSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                val itemSelect = genreSpin.getItemAtPosition(p2) as String
                // CHANGE THIS TO DEFAULT OPTION
                if (itemSelect != "None"){
                    selectGenre = itemSelect
                }
                button.visibility = View.VISIBLE
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                button.visibility = View.VISIBLE
            }
        }

        /*
        Set up search field edit and grab keyword(s) upon completion, then closes keyboard
        */
        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Hide the keyboard
                val keyboard =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(searchBar.windowToken, 0)

                // Store the entered text
                selectTitle = searchBar.text.toString()

                // Log the entered text for debugging
                Log.d("EditorActionListener", "Entered text: $selectTitle")

                // Return true to indicate that you've handled the action
                return@setOnEditorActionListener true
            }

            // Return false for other actions
            false
        }

        // Set a TextWatcher to capture changes in the EditText text
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // This method is called to notify you that somewhere within s, the text has been changed
                // Update selectTitle here
                selectTitle = s?.toString() ?: ""

                // Log the entered text for debugging
                Log.d("TextWatcher", "Entered text: $selectTitle")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify you that somewhere within s, the text is about to be changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify you that somewhere within s, the text has been changed
            }
        })

        /* Set click action for search (right now set to making toasts,
        will need API team to hook up the keyword, language, genre query request*/
        button.setOnClickListener{
            if(selectLang == "") {
                val toastText = "PLEASE CHOOSE A SPOKEN LANGUAGE"
                Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
            } else {
                if(selectTitle == "") {
                    //use discover api endpoint
                    makeDiscoverApiRequest(selectLang, selectGenre)
                } else {
                    //use search api endpoint
                    makeSearchApiRequest(selectLang, selectGenre, selectTitle)
                }

                // hides the keyboard when the search button is clicked
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(searchBar.windowToken, 0)

                // populates the recyclerview (will need to fix this approach if possible)
                val searchResultsImageView: ShapeableImageView = findViewById(R.id.search_results)
                searchResultsImageView.visibility = View.VISIBLE
                val recycleAdapter = MovieAdapter(arrayOfMovies)
                recyclerView.adapter = recycleAdapter
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.visibility = View.VISIBLE
            }
        }
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
                    genres = parseMovieGenres(JSONObject(responseBody))
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

    private fun populateGenreSpinner(genres: List<String>) {
        val genreSpin: Spinner = findViewById(R.id.genre_dd)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres.toTypedArray())

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        genreSpin.adapter = adapter
    }

    private fun parseMovieGenres(response: JSONObject): HashMap<String, Int> {
        val genresMap = HashMap<String, Int>()
        val genres = mutableListOf<String>()
        genres.add("None")

        // Check if the response contains the "genres" array
        if (response.has("genres")) {
            val genresArray = response.getJSONArray("genres")
            for (i in 0 until genresArray.length()) {
                val genreObject = genresArray.getJSONObject(i)
                val genreName = genreObject.getString("name")
                val genreId = genreObject.getInt("id")
                genresMap.put(genreName, genreId)
                genres.add(genreName)
            }
        }

        // Populate the genre spinner with the obtained genres
        populateGenreSpinner(genres)

        return genresMap
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
                        languages = parseConfigurationLanguages(JSONArray(responseBody))
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

    private fun parseConfigurationLanguages(response: JSONArray): HashMap<String, String> {
        val languagesMap = HashMap<String, String>()
        val languageNames = mutableListOf<String>()

        for (i in 0 until response.length()) {
            val languageObject = response.getJSONObject(i)
            val langName = languageObject.getString("english_name")
            val langId = languageObject.getString("iso_639_1")
            languagesMap[langName] = langId
            languageNames.add(langName)
        }

        // Sort the language names alphabetically
        languageNames.sort()

        // Add "No Language" as the first item
        languageNames.add(0, "No Language")

        // Populate the languages spinner with the obtained languages
        populateLanguagesSpinner(languageNames)

        return languagesMap
    }

    private fun populateLanguagesSpinner(languages: List<String>) {
        val spokeSpin: Spinner = findViewById(R.id.spoken_dd)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages.toTypedArray())

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spokeSpin.adapter = adapter
    }

    private fun makeDiscoverApiRequest(language: String, genre: String?) {
        val client = AsyncHttpClient()
        val apiUrl = "https://api.themoviedb.org/3/discover/movie"

        val params = RequestParams()
        params["api_key"] = "d027ce23bad3a259cffc5f57a3fbeb09"
        params["include_adult"] = "false"
        val langId = languages[language]
        params["with_original_language"] = langId
        if(genre != "") {
            val genreId = genres[genre]
            params["with_genres"] = genreId.toString()
        }

        client.get(apiUrl, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, responseBody: String?) {
                // Handle the successful response here
                if (!responseBody.isNullOrBlank()) {
                     // Parse the JSON response
                    val movies = parseDiscoverMovies(JSONObject(responseBody))
                    Log.d("searchCriteria", "Queried data: $language, $genre")
                    Log.d("getMovieList", "Queried Movies: $movies")
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

    private fun makeSearchApiRequest(language: String, genre: String?, title: String) {
        val client = AsyncHttpClient()
        val apiUrl = "https://api.themoviedb.org/3/search/movie"

        val params = RequestParams()
        params["api_key"] = "d027ce23bad3a259cffc5f57a3fbeb09"
        params["include_adult"] = "false"
        params["query"] = title

        client.get(apiUrl, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, responseBody: String?) {
                // Handle the successful response here
                if (!responseBody.isNullOrBlank()) {
                    // Parse the JSON response
                    val movies = parseSearchMovies(JSONObject(responseBody), language, genre)

                    // Update UI with filtered movies
                    updateUIWithMovies(movies)
                    Log.d("searchCriteria", "Queried data: $language, $genre")
                    Log.d("getMovieList", "Queried Movies: $movies")
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
    private fun parseDiscoverMovies(response: JSONObject): Array<Map<String, Any>> {
        val moviesList = mutableListOf<Map<String, Any>>()
        var genreStringList = mutableListOf<String>()

        if (response.has("results")) {
            val resultsArray = response.getJSONArray("results")
            for (i in 0 until resultsArray.length()) {
                genreStringList = mutableListOf<String>()
                val movieObject = resultsArray.getJSONObject(i)

                val genreList = movieObject.getJSONArray("genre_ids")

                for (j in 0 until genreList.length()) {
                    val genreString = genres.entries.find { it.value == genreList[j] }!!.key
                    genreStringList.add(genreString)
                }

                val movieMap = mapOf(
                    "poster_path" to "https://image.tmdb.org/t/p/original" + movieObject.getString("poster_path"),
                    "title" to movieObject.getString("title"),
                    "genre" to genreStringList,
                    "overview" to movieObject.getString("overview"),
                    "rating" to if (movieObject.has("vote_average")) movieObject.getString("vote_average") else "",
                    "original_language" to movieObject.getString("original_language")
                )
                moviesList.add(movieMap)
            }
        }
        return moviesList.toTypedArray()
    }

    private fun parseSearchMovies(response: JSONObject, language: String, genre: String?): Array<Map<String, Any>> {
        val moviesList = mutableListOf<Map<String, Any>>()
        var genreStringList: MutableList<String>
        val genreId = genres[genre]
        val languageId = languages[language]

        // Check if the response contains the "results" array
        if (response.has("results")) {
            val resultsArray = response.getJSONArray("results")
            var hasGenre = false
            for (i in 0 until resultsArray.length()) {
                genreStringList = mutableListOf<String>()
                hasGenre = false
                val movieObject = resultsArray.getJSONObject(i)
                val genreList = movieObject.getJSONArray("genre_ids")

                for (j in 0 until genreList.length()) {
                    if (genre != "" && genreList[j] == genreId) {
                        hasGenre = true
                    }
                    val genreString = genres.entries.find { it.value == genreList[j] }!!.key
                    genreStringList.add(genreString)
                }

                if (movieObject.getString("original_language") == languageId) {
                    if (genre == "") {
                        val movieMap = mapOf(
                            "poster_path" to "https://image.tmdb.org/t/p/original" + movieObject.getString( "poster_path"),
                            "title" to movieObject.getString("title"),
                            "genre" to genreStringList,
                            "overview" to movieObject.getString("overview"),
                            "rating" to if (movieObject.has("vote_average")) movieObject.getString("vote_average") else "",
                            "original_language" to movieObject.getString("original_language")
                        )
                        moviesList.add(movieMap)
                    } else if (hasGenre) {
                        val movieMap = mapOf(
                            "poster_path" to "https://image.tmdb.org/t/p/original" + movieObject.getString("poster_path"),
                            "title" to movieObject.getString("title"),
                            "genre" to genreStringList,
                            "overview" to movieObject.getString("overview"),
                            "rating" to if (movieObject.has("vote_average")) movieObject.getString("vote_average") else "",
                            "original_language" to movieObject.getString("original_language")
                        )
                        moviesList.add(movieMap)
                    }

                }
            }
        }

        return moviesList.toTypedArray()
    }

    private fun updateUIWithMovies(movies: Array<Map<String, Any>>) {
        val recyclerView: RecyclerView = findViewById(R.id.movieRecyclerView)
        val movieAdapter = MovieAdapter(movies)

        // Set the adapter to the RecyclerView
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.visibility = View.VISIBLE

        movieAdapter.setOnClickListener(object:
            MovieAdapter.OnClickListener {
                override fun onClick(position: Int, movie: Map<String, Any>) {
                    val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                    intent.putExtra(DETAIL_SCREEN, movie as Serializable)
                    startActivity(intent)
                }
            }
        )
    }

    companion object {
        val DETAIL_SCREEN="movie_details_screen"
    }
}