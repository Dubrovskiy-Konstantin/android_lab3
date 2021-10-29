package com.example.mediaplayer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.VideoView
import java.lang.NullPointerException

class FullScreenActivity : AppCompatActivity() {
    var file = ""
    var currentPosition = 0
    lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        }
        catch (e: NullPointerException) {
        }
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_full_screen)
        videoView = findViewById(R.id.fullScreenVideoView)
        var mediaController =
            CustomMediaController(this)
        mediaController.setListener {
            back()
        }
        videoView.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoView)

        if(intent.extras != null){
            file = intent.extras!!.getString("file") as String
            currentPosition = intent.extras!!.get("currentPosition") as Int
        }
        else
            return
        videoView.setVideoURI(Uri.parse(file))
        videoView.seekTo(currentPosition)
        videoView.start()
    }

    fun back(){
        val intent = Intent()
        intent.putExtra("currentPosition", videoView.currentPosition)
        setResult(RESULT_OK, intent)
        finish()
    }
}