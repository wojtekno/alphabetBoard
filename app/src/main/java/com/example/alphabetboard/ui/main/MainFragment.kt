package com.example.alphabetboard.ui.main

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alphabetboard.R
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber.d

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        d("onCreateView")
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        d("onViewCreated")
        requireActivity().window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
//        requireActivity().actionBar?.hide()


        val screenSize = measureScreen()

        val itemsMatrix = viewModel.getItemsMatrix(screenSize.first, screenSize.second)
        d("screen $screenSize \nsquare ${itemsMatrix.second} x ${itemsMatrix.third}")
        main.doOnLayout {
            main.measure(0, 0)
            val (layoutWidth, layoutHeight) = measureView(main)
            val iMatrix = viewModel.getItemsMatrix(layoutWidth, layoutHeight)
            val adapter = LetterAdapter(iMatrix.second to iMatrix.third)
            letter_rv.adapter = adapter
            letter_rv.layoutManager = GridLayoutManager(requireContext(), itemsMatrix.first)
            adapter.submitList(viewModel.generateLetterTiles())
        }


    }


    private fun measureScreen(): Pair<Int, Int> {
        val metrics = DisplayMetrics()
        requireActivity().windowManager
//            .currentWindowMetrics
            .defaultDisplay.getMetrics(metrics)

        val height = metrics.heightPixels
        val width = metrics.widthPixels
        d("w : $width  h : $height ")
        return width to height
    }

    private fun measureView(view: View): Pair<Int, Int> {
        view.measure(0, 0)
        val height = view.height
        val width = view.width
        d("w : $width  h : $height ")
        return width to height
    }


}