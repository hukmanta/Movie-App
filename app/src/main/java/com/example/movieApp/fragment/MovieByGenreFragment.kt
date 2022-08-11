package com.example.movieApp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieApp.adapter.MovieRecyclerViewAdapter
import com.example.movieApp.adapter.PaginationScrollListener
import com.example.movieApp.adapter.RecyclerViewClickListener
import com.example.movieApp.databinding.FragmentMovieByGenreBinding
import com.example.movieApp.model.getMovieDetail.GetMovieDetailResponse
import com.example.movieApp.model.getReview.GetReviewResponse
import com.example.movieApp.model.getVideos.GetVideosResponse
import com.example.movieApp.service.RetrofitBuilder
import com.example.movieApp.util.serverError
import com.example.movieApp.util.showSnackBarLong
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieByGenreFragment : Fragment(), RecyclerViewClickListener {

    private val _movieAPI = RetrofitBuilder.movieAPI
    private lateinit var _binding: FragmentMovieByGenreBinding
    private var _getMovieDetailResponse: GetMovieDetailResponse? = null
    private var _getReviewResponse: GetReviewResponse? = null
    private var _getVideosResponse: GetVideosResponse? = null
    private val _getMovieByGenreResponse: MovieByGenreFragmentArgs by navArgs()
    private lateinit var _recycleView: RecyclerView
    private lateinit var _adapter: MovieRecyclerViewAdapter
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieByGenreBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(
            this.requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        _adapter = MovieRecyclerViewAdapter(this.requireContext(), this)
        _adapter.data = _getMovieByGenreResponse.movieList.results
        _recycleView = _binding.genderRV
        _recycleView.addOnScrollListener(object : PaginationScrollListener(
            layoutManager,
            _getMovieByGenreResponse.movieList.totalPages ?: 1,
            currentPage
        ) {

            override fun loadMoreItems() {
                loadMoreData(_getMovieByGenreResponse.genreId.toInt(), ++currentPage)
            }
        })
        _recycleView.adapter = _adapter
        _recycleView.layoutManager = layoutManager
        return _binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun recyclerViewListClick(v: View, position: Int) {
        _binding.movieProgressBar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(
                "MOVIE_BY_GENRE_FRAGMENT",
                _getMovieByGenreResponse.movieList.results?.get(position).toString()
            )
            getMovieDetail(_getMovieByGenreResponse.movieList.results?.get(position)?.id!!)
            Log.d("MOVIE_BY_GENRE_FRAGMENT", _getMovieDetailResponse.toString())
            getMovieReview(_getMovieByGenreResponse.movieList.results?.get(position)?.id!!)
            Log.d("MOVIE_BY_GENRE_FRAGMENT", _getReviewResponse.toString())
            getVideos(_getMovieByGenreResponse.movieList.results?.get(position)?.id!!)
            Log.d("MOVIE_BY_GENRE_FRAGMENT", _getVideosResponse.toString())
            if (_getMovieDetailResponse != null && _getReviewResponse != null && _getVideosResponse != null) {
                val action =
                    MovieByGenreFragmentDirections.actionMovieByGenreFragmentToMovieDetailFragment(
                        _getMovieDetailResponse!!,
                        _getReviewResponse!!,
                        _getVideosResponse!!
                    )
                withContext(Dispatchers.Main) { _binding.root.findNavController().navigate(action) }
            }
        }
    }

    private fun getMovieDetail(id: Int) = runBlocking {
        withContext(Dispatchers.IO) {
            try {
                val result = _movieAPI.getMovieDetailAsync(id = id)
                _getMovieDetailResponse = result
            } catch (e: HttpException) {
                if (e.code() != 500) {
                    val body = e.response()!!.errorBody()
                    val errorConverter = RetrofitBuilder.errorConverter
                    val errorParser = errorConverter.convert(body!!)
                    withContext(Dispatchers.Main) {
                        showSnackBarLong(errorParser?.statusMessage ?: e.message())
                        Log.e(
                            activity?.javaClass?.simpleName,
                            errorParser?.statusMessage ?: e.message()
                        )
                    }
                } else {
                    serverError(e)
                }
            } catch (e: TimeoutException) {
                withContext(Dispatchers.Main) {
                    showSnackBarLong("Request Timeout")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showSnackBarLong("Unknown Error")
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getMovieReview(id: Int) = runBlocking {
        withContext(Dispatchers.IO) {
            try {
                val result =
                    _movieAPI.getUserReviewAsync(id = id, page = 1)
                _getReviewResponse = result
            } catch (e: HttpException) {
                if (e.code() != 500) {
                    val body = e.response()!!.errorBody()
                    val errorConverter = RetrofitBuilder.errorConverter
                    val errorParser = errorConverter.convert(body!!)
                    withContext(Dispatchers.Main) {
                        showSnackBarLong(errorParser?.statusMessage ?: e.message())
                        Log.e(
                            activity?.javaClass?.simpleName,
                            errorParser?.statusMessage ?: e.message()
                        )
                    }
                } else {
                    serverError(e)
                }
            } catch (e: TimeoutException) {
                withContext(Dispatchers.Main) {
                    showSnackBarLong("Request Timeout")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showSnackBarLong("Unknown Error")
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getVideos(id: Int) = runBlocking {
        withContext(Dispatchers.IO) {
            try {
                val result = _movieAPI.getVideoAsync(id = id)
                _getVideosResponse = result
            } catch (e: HttpException) {
                if (e.code() != 500) {
                    val body = e.response()!!.errorBody()
                    val errorConverter = RetrofitBuilder.errorConverter
                    val errorParser = errorConverter.convert(body!!)
                    withContext(Dispatchers.Main) {
                        showSnackBarLong(errorParser?.statusMessage ?: e.message())
                        Log.e(
                            activity?.javaClass?.simpleName,
                            errorParser?.statusMessage ?: e.message()
                        )
                    }
                } else {
                    serverError(e)
                }
            } catch (e: TimeoutException) {
                withContext(Dispatchers.Main) {
                    showSnackBarLong("Request Timeout")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showSnackBarLong("Unknown Error")
                    e.printStackTrace()
                }
            }
        }
    }


    private fun loadMoreData(position: Int, page: Int = 1) = runBlocking {
        try {
            _binding.movieProgressBar.visibility = View.VISIBLE
            val result = withContext(Dispatchers.Main) {
                _movieAPI.getMovieByGenreAsync(
                    with_genres = position,
                    page = page
                )
            }
            result.results?.let { list ->
                list.forEach { element ->
                    element?.let { it ->
                        _adapter.data?.plus(it)
                    }
                }
            }
        } catch (e: HttpException) {
            if (e.code() != 500) {
                val body = e.response()!!.errorBody()
                val errorConverter = RetrofitBuilder.errorConverter
                val errorParser = withContext(Dispatchers.IO) {
                    errorConverter.convert(body!!)
                }
                withContext(Dispatchers.Main) {
                    showSnackBarLong(errorParser?.statusMessage ?: e.message())
                    Log.e(
                        activity?.javaClass?.simpleName,
                        errorParser?.statusMessage ?: e.message()
                    )

                }
            } else {
                serverError(e)
            }
        } catch (e: TimeoutException) {
            withContext(Dispatchers.Main) {
                showSnackBarLong("Request Timeout")
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                showSnackBarLong("Unknown Error")
                e.printStackTrace()
            }
        } finally {
            _binding.movieProgressBar.visibility = View.GONE
        }
    }
}