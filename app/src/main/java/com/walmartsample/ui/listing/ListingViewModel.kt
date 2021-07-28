package com.walmartsample.ui.listing

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walmartsample.data.MovieRepository
import com.walmartsample.model.TrendingMovieResponse
import kotlinx.coroutines.flow.collect
import com.walmartsample.model.Result
import kotlinx.coroutines.launch

/**
 * ViewModel for ListingActivity
 */
class ListingViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _movieList = MutableLiveData<Result<TrendingMovieResponse>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchTrendingMovies().collect {
                _movieList.value = it
            }
        }
    }
}