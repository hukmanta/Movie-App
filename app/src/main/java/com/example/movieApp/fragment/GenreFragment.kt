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
import com.example.movieApp.adapter.GenreRecyclerViewAdapter
import com.example.movieApp.adapter.RecyclerViewClickListener
import com.example.movieApp.databinding.FragmentGenreBinding
import com.example.movieApp.model.getMovieByGenre.GetMovieByGenreResponse
import com.example.movieApp.service.RetrofitBuilder
import com.example.movieApp.util.serverError
import com.example.movieApp.util.showSnackBarLong
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GenreFragment : Fragment(), RecyclerViewClickListener {

    private val _movieAPI = RetrofitBuilder.movieAPI
    private lateinit var _binding: FragmentGenreBinding
    private val _getGenreResponse: GenreFragmentArgs by navArgs()
    private var _getMovieByGenreResponse: GetMovieByGenreResponse? = null
    private lateinit var _recycleView: RecyclerView
    private lateinit var _adapter: GenreRecyclerViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _adapter = GenreRecyclerViewAdapter(this.requireContext(), this)
        _adapter.data = _getGenreResponse.genreList.genres
        _recycleView = _binding.genderRV
        _recycleView.adapter = _adapter
        _recycleView.layoutManager = LinearLayoutManager(
            this.requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        super.onViewCreated(view, savedInstanceState)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun recyclerViewListClick(v: View, position: Int) {
        _binding.genreProgressBar.visibility = View.VISIBLE
        Log.d("GENRE_FRAGMENT", _getGenreResponse.genreList.genres?.get(position).toString())
        GlobalScope.launch(Dispatchers.IO) {
            getMovieData(_getGenreResponse.genreList.genres?.get(position)?.id!!)
            withContext(Dispatchers.Main) {
                if (_getMovieByGenreResponse != null) {
                    Log.d("GENRE_FRAGMENT", _getMovieByGenreResponse.toString())
                    val action =
                        GenreFragmentDirections.actionGenreFragmentToMovieByGenreFragment(
                            _getMovieByGenreResponse!!,
                            _getGenreResponse.genreList.genres?.get(position)?.id!!.toString()
                        )
                    _binding.root.findNavController().navigate(action)

                }
            }
        }
    }

    private fun getMovieData(position: Int, page: Int = 1) = runBlocking {
        try {
            val result = _movieAPI.getMovieByGenreAsync(
                with_genres = position,
                page = page
            )

            _getMovieByGenreResponse = if (result.results != null) {
                result
            } else {
                null
            }
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