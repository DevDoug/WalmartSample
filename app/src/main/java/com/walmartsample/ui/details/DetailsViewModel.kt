package com.walmartsample.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.walmartsample.data.MovieRepository
import io.buildwithnd.demotmdb.model.MovieDesc
import kotlinx.coroutines.flow.onStart
import com.walmartsample.model.Result
import kotlinx.coroutines.flow.collect


/**
 * ViewModel for Movie details screen
 */
class DetailsViewModel  @ViewModelInject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private var _id = MutableLiveData<Int>()
    private val _movie: LiveData<Result<MovieDesc>> = _id.distinctUntilChanged().switchMap {
        liveData {
            movieRepository.fetchMovie(it).onStart {
                emit(Result.loading())
            }.collect { emit(it) }
        }
    }
    val movie = _movie

    fun getMovieDetail(id: Int) {
        _id.value = id
    }
}