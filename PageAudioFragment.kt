package com.example.mediaplayer

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import java.io.FileNotFoundException
import java.io.Serializable


class PageAudioFragment(file: String) : PageFragment(file) {
    public override var mode: String = "audio"
    lateinit var textView: TextView
    lateinit var audioPlayer : VideoView
    var test = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //file = if (arguments != null) requireArguments().getString("file")!! else throw FileNotFoundException()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result: View = inflater.inflate(R.layout.audio_fragment_page, container, false)
        textView = result.findViewById<TextView>(R.id.displayAudio)


        return result
    }

    companion object {
        fun newInstance(fragment: PageAudioFragment): PageAudioFragment {
//            val args = Bundle()
//            args.putString("file", fragment.file)
//            fragment.arguments = args
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        val myAudioUri = Uri.parse(file)
        audioPlayer.setVideoURI(myAudioUri)
        val mediaController = MediaController(context)
        audioPlayer.setMediaController(mediaController)
        mediaController.setMediaPlayer(audioPlayer)

        textView.text = "START"
    }

    override fun onResume() {
        super.onResume()
        audioPlayer.start()
        textView.text = "RESUME"
    }
}