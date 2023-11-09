package com.osu.cinespeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // set default view
        setContentView(R.layout.activity_main)

        // initialize the filter parameters


        // access to the searchbar
        val searchBar: EditText = findViewById(R.id.search_title)

        // access the spinner elements themselves
        val spokeSpin: Spinner  = findViewById(R.id.spoken_dd)
        val subSpin: Spinner = findViewById(R.id.subtitle_dd)
        val genreSpin: Spinner = findViewById(R.id.genre_dd)

        // placeholder arrays for spinner population (REPLACE THIS WITH
        // ACTUAL LISTS FROM TMDB)
        val spokeOptions = R.array.lang_array
        val subOptions = R.array.subtitle_array
        val genreOptions = R.array.genre_array

        // set the entries for the spinners
        populateSpinner(spokeSpin, spokeOptions)
        populateSpinner(subSpin, subOptions)
        populateSpinner(genreSpin, genreOptions)

    }

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