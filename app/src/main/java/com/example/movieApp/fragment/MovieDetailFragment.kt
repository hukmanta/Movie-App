package com.example.movieApp.fragment

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieApp.R
import com.example.movieApp.adapter.RecyclerViewClickListener
import com.example.movieApp.adapter.ReviewRecyclerViewAdapter
import com.example.movieApp.databinding.FragmentMovieDetailBinding
import com.example.movieApp.viewModel.MovieDetailFragmentViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView


class MovieDetailFragment : Fragment(), RecyclerViewClickListener {

    private val _arg: MovieDetailFragmentArgs by navArgs()
    private lateinit var _viewBinding: FragmentMovieDetailBinding
    private lateinit var _adapter: ReviewRecyclerViewAdapter
    private lateinit var _recycleView: RecyclerView
    private lateinit var _video: StyledPlayerView
    private lateinit var player: ExoPlayer


    private lateinit var viewModel: MovieDetailFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = MovieDetailFragmentViewModel(_arg.movieDetail, _arg.reviewDetail, _arg.videos)

        _viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        _viewBinding.viewModel = viewModel
        _adapter = ReviewRecyclerViewAdapter(this.requireContext(), this)
        _adapter.data = _arg.reviewDetail.results
        _recycleView = _viewBinding.reviewRV
        _recycleView.adapter = _adapter
        _recycleView.layoutManager = LinearLayoutManager(
            this.requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        _video = _viewBinding.videoView
        return _viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player =   ExoPlayer.Builder(this.requireContext()).build()
        val listVideo = _arg.videos.results
        if( listVideo!= null && listVideo.isNotEmpty()) {
            val urlStr =
                "https://www.themoviedb.org/video/play?key=${listVideo[0]?.key}"
            Log.d("MOVIE_DETAIL_FRAGMENT", urlStr)
            initializedPlayer(
                //Uri.parse("https://www.youtube.com/watch?v=Go8nTmfrQd8")
                Uri.parse(urlStr)
            )
        }
    }

    override fun recyclerViewListClick(v: View, position: Int) {
        //DO("Not need to implemented")
    }

    private fun initializedPlayer(videoUri: Uri) {
        // Build the media item.
        val mediaItem: MediaItem = MediaItem.fromUri(videoUri)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Start the playback.
        player.play()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            player.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        player.stop()
    }

}