package com.example.mediaplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class PageVideoFragment : PageFragment() {
    private var pageNumber = 0

    override var mode: String = "video"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result: View = inflater.inflate(R.layout.video_fragment_page, container, false)
        val pageHeader = result.findViewById<TextView>(R.id.displayVideo)
        val header = "Видео фрагмент " + (pageNumber + 1)
        pageHeader.text = header
        return result
    }

    companion object {
        fun newInstance(page: Int): PageVideoFragment {
            val fragment = PageVideoFragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }
}