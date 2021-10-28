package com.example.mediaplayer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener
import androidx.viewpager2.widget.ViewPager2
import android.widget.*
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback


var PACKAGE_NAME : String = ""

class MainActivity : AppCompatActivity() {
    private val _requestcode = 1
    //var pages = ArrayList<PageFragment>()
    lateinit var chooseAudioButton : Button
    lateinit var chooseVideoButton: Button
    lateinit var viewPager: ViewPager2
    lateinit var videoView: VideoView
    lateinit var mediaPlayer: MediaPlayer
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PACKAGE_NAME = packageName

        viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = FragmentAdapter(this)
        chooseAudioButton = findViewById<Button>(R.id.buttonChooseAudio)
        chooseVideoButton = findViewById<Button>(R.id.buttonChooseVideo)
        mediaPlayer = MediaPlayer()

        textView = findViewById(R.id.textView)

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                //textView.text = "Начинаем двигать ${position}|$positionOffset|$positionOffsetPixels"
                //Toast.makeText(this@MainActivity, "Начинаем двигать ${position.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                textView.text = "Выбрана позиция ${position.toString()}"
//                if(mediaPlayer.isPlaying) mediaPlayer.stop()
//                mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.test_music)
//                mediaPlayer.setOnCompletionListener(OnCompletionListener { mediaPlayer.stop() })
//                mediaPlayer.start()
                //Toast.makeText(this@MainActivity, "Выбрана позиция ${position.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                //textView.text = "Скрол стэйт ${state.toString()}"
                //Toast.makeText(this@MainActivity, "Скрол ст ${state.toString()}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    public fun nextPage(view: View){

    }

    public fun prevPage(view: View){

    }

//    fun updatePages(){
//        val position = viewPager.currentItem
//        val pageAdapter: FragmentStateAdapter = FragmentAdapter(this, pages)
//        viewPager.adapter = pageAdapter
//        viewPager.setCurrentItem(position, false)
//        val x = viewPager.adapter as FragmentAdapter
//        x.add(PageAudioFragment())
//    }

    fun chooseAudio(view: View){
        val adapter = viewPager.adapter as FragmentAdapter
        var file = "android.resource://" + packageName + "/" + R.raw.test_music
        adapter.addItem(PageAudioFragment(file))
        //(adapter.getItemAt(viewPager.currentItem) as PageAudioFragment).setText("asdasdas")
        //pages.add(PageAudioFragment())
        //updatePages()
    }

    fun chooseVideo(view: View){
        val adapter = viewPager.adapter as FragmentAdapter
        var file = "android.resource://" + packageName + "/" + R.raw.test_video
        adapter.addItem(PageVideoFragment(file, this))
        //adapter.addItem(PageVideoFragment("android.resource://" + packageName + "/" + R.raw.test_music))

        //pages.add(PageVideoFragment())
        //updatePages()
        //var a = viewPager[viewPager.currentItem]
        //var frag = fragmentManager.findFragmentById(R.id.vide)
    }


    override fun onActivityResult(requestcode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestcode, resultCode, data)
        if(requestcode == this._requestcode && resultCode == Activity.RESULT_OK){
            if(data == null)
                return
            val uri = data.data
            if (uri != null) {
                copyFile(uri)
                //Toast.makeText(applicationContext, uri.path, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openMusicChooser(view: View){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "audio/*|video/*"
        startActivityForResult(intent, _requestcode)
    }

    private fun copyFile(uri : Uri){
        try {
            //val inputStream: InputStream? = getContentResolver().openInputStream(uri)
            //val lineList = mutableListOf<String>()
            //inputStream?.bufferedReader()?.forEachLine { lineList.add(it) }
            //_setBusList(lineList)
        }
        catch (e:Exception){
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}