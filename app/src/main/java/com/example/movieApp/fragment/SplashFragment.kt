package com.example.movieApp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movieApp.databinding.FragmentSplashBinding
import com.example.movieApp.model.getGenre.GetGenreResponse
import com.example.movieApp.service.RetrofitBuilder
import com.example.movieApp.util.serverError
import com.example.movieApp.util.showSnackBarLong
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SplashFragment : Fragment() {

    private var movieAPI = RetrofitBuilder.movieAPI

    private var _binding: FragmentSplashBinding? = null
    private var _genreResponse: GetGenreResponse? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getGenreData()
    }


    @Suppress("BlockingMethodInNonBlockingContext")
    @OptIn(DelicateCoroutinesApi::class)
    private fun getGenreData() = GlobalScope.launch(Dispatchers.Main) {
        try {
                binding.progressBar.visibility = View.VISIBLE
                val result = withContext(Dispatchers.IO) { movieAPI.getGenreAsync() }

                _genreResponse = if (result.genres != null) {
                    result
                } else {
                    GetGenreResponse(genres = null)
                }

        } catch (e: HttpException) {
            if (e.code() != 500) {
                val body = e.response()!!.errorBody()
                val errorConverter = RetrofitBuilder.errorConverter
                val errorParser = withContext(Dispatchers.IO) {
                    errorConverter.convert(body!!)
                }
                    showSnackBarLong(errorParser?.statusMessage ?: e.message())
                    Log.e(
                        activity?.javaClass?.simpleName,
                        errorParser?.statusMessage ?: e.message()
                    )


            } else {
                serverError(e)
            }
        } catch (e: TimeoutException) {
                showSnackBarLong("Request Timeout")
        } catch (e: Exception) {
                showSnackBarLong("Unknown Error")
                e.printStackTrace()

        } finally {
            binding.progressBar.visibility = View.GONE
            Log.d("GENRE_RESPONSE", _genreResponse.toString())
            if (_genreResponse != null) {
                val action =
                    SplashFragmentDirections.actionSplashFragmentToGenreFragment(_genreResponse!!)
                binding.root.findNavController().navigate(action)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}