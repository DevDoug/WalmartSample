package com.walmartsample.data

import com.walmartsample.data.remote.MovieRemoteDatasource
import com.walmartsample.data.local.MovieDao
import com.walmartsample.model.Result
import com.walmartsample.model.TrendingMovieResponse
import io.buildwithnd.demotmdb.model.MovieDesc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDatasource,
    private val movieDao: MovieDao
) {

    suspend fun fetchTrendingMovies(): Flow<Result<TrendingMovieResponse>?> {
        return flow {
            emit(fetchTrendingMoviesCached())
            emit(Result.loading())
            val result = movieRemoteDataSource.fetchTrendingMovies()

            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    movieDao.deleteAll(it)
                    movieDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingMoviesCached(): Result<TrendingMovieResponse>? =
        movieDao.getAll()?.let {
            Result.success(TrendingMovieResponse(it))
        }

    suspend fun fetchMovie(id: Int): Flow<Result<MovieDesc>> {
        return flow {
            emit(Result.loading())
            emit(movieRemoteDataSource.fetchMovie(id))
        }.flowOn(Dispatchers.IO)
    }
}