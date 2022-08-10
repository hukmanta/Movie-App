package com.example.movieApp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.movieApp.R
import com.example.movieApp.databinding.FragmentGenreBinding
import com.example.movieApp.viewModel.GenreListViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val _getGenreResponse: GenreFragmentArgs by navArgs()
    private lateinit var _viewModel: GenreListViewModel
    private lateinit var _recycleView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        _viewModel = GenreListViewModel(_getGenreResponse.genreList)
        _recycleView = _binding!!.root.findViewById(R.id.genderRV)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}