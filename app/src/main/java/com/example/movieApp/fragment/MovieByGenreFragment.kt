package com.example.movieApp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieApp.adapter.MovieRecyclerViewAdapter
import com.example.movieApp.adapter.RecyclerViewClickListener
import com.example.movieApp.databinding.FragmentMovieByGenreBinding
import com.example.movieApp.service.RetrofitBuilder

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieByGenreFragment : Fragment(), RecyclerViewClickListener {

    private  val _movieAPI = RetrofitBuilder.movieAPI
    private var _binding: FragmentMovieByGenreBinding? = null
    private val _getMovieByGenreResponse: MovieByGenreFragmentArgs by navArgs()
    private lateinit var _recycleView: RecyclerView
    private  lateinit var _adapter: MovieRecyclerViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentMovieByGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _adapter = MovieRecyclerViewAdapter(this.requireContext(), this)
        _adapter.data = _getMovieByGenreResponse.movieList.results
        _recycleView = _binding!!.genderRV
        _recycleView.adapter = _adapter
        _recycleView.layoutManager = LinearLayoutManager(
            this.requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun recyclerViewListClick(v: View, position: Int) {
        Log.d("GENRE_FRAGMENT", _getMovieByGenreResponse.movieList.results?.get(position).toString())
        //getMovieData(_getMovieByGenreResponse.movieList.results?.get(position)?.id!!)
        //Log.d("GENRE_FRAGMENT", _getMovieByGenreResponse.toString())
    }

//    private fun getMovieData(position: Int, page: Int = 1) = runBlocking {
//        try {
//            binding.genderProgressBar.visibility = View.VISIBLE
//            val result = withContext(Dispatchers.IO) { _movieAPI.getMovieByGenreAsync(with_genres = position, page = page) }
//            _getMovieByGenreResponse = if (result.results != null) {
//                result
//            } else {
//                null
//            }
//        } catch (e: HttpException) {
//            if (e.code() != 500) {
//                val body = e.response()!!.errorBody()
//                val errorConverter = RetrofitBuilder.errorConverter
//                val errorParser = withContext(Dispatchers.IO) {
//                    errorConverter.convert(body!!)
//                }
//                withContext(Dispatchers.Main) {
//                    showSnackBarLong(errorParser?.statusMessage ?: e.message())
//                    Log.e(
//                        activity?.javaClass?.simpleName,
//                        errorParser?.statusMessage ?: e.message()
//                    )
//
//                }
//            } else {
//                serverError(e)
//            }
//        } catch (e: TimeoutException) {
//            withContext(Dispatchers.Main) {
//                showSnackBarLong("Request Timeout")
//            }
//        } catch (e: Exception) {
//            withContext(Dispatchers.Main) {
//                showSnackBarLong("Unknown Error")
//                e.printStackTrace()
//            }
//        } finally {
//            binding.genderProgressBar.visibility = View.GONE
//        }
//    }


}