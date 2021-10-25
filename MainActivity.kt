package com.example.mediaplayer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import android.widget.*


class MainActivity : AppCompatActivity() {
    private val _requestcode = 1
    var pages = ArrayList<PageFragment>()
    lateinit var chooseAudioButton : Button
    lateinit var chooseVideoButton: Button
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById<ViewPager2>(R.id.viewPager)
        chooseAudioButton = findViewById<Button>(R.id.buttonChooseAudio)
        chooseVideoButton = findViewById<Button>(R.id.buttonChooseVideo)
    }

    fun updatePages(){
        val position = viewPager.currentItem
        val pageAdapter: FragmentStateAdapter = FragmentAdapter(this, pages)
        viewPager.adapter = pageAdapter
        viewPager.setCurrentItem(position, false)
    }

    fun chooseAudio(view: View){
        pages.add(PageAudioFragment())
        updatePages()
    }

    fun chooseVideo(view: View){
        pages.add(PageVideoFragment())
        updatePages()
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