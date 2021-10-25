package com.example.mediaplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class PageAudioFragment : PageFragment() {
    public override var mode: String = "audio"
    private var pageNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result: View = inflater.inflate(R.layout.audio_fragment_page, container, false)
        val pageHeader = result.findViewById<TextView>(R.id.displayAudio)
        val header = "Аудио фрагмент " + (pageNumber + 1)
        pageHeader.text = header
        return result
    }

    companion object {
        fun newInstance(page: Int): PageAudioFragment {
            val fragment = PageAudioFragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }
}