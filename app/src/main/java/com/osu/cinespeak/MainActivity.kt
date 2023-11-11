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
import com.google.android.material.button.MaterialButton


class MainActivity: AppCompatActivity(){

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
                val itemSelect = spokeSpin.getItemAtPosition(p2) as String
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

            // populates the recyclerview (will need to fix this approach if possible)
            val recycleAdapter = MovieAdapter(arrayOfMovies)
            recyclerView.adapter = recycleAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.visibility = View.VISIBLE
        }

        // Create methods to grab movie data (TODO API/backend team)
        /* CHANGE THE ARRAY arrayOfMovies TO FIT YOUR PARSED DATA*/


    }

    // set adapters for spinners
    fun populateSpinner(
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