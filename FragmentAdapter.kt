package com.example.mediaplayer

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


class FragmentAdapter(fragmentActivity: FragmentActivity?, pages : ArrayList<PageFragment>) : FragmentStateAdapter(fragmentActivity!!) {
    var pages = pages


    override fun createFragment(position: Int): Fragment {
        if(pages[position].mode == "audio")
            return PageAudioFragment.newInstance(position)
        else if (pages[position].mode == "video")
            return PageVideoFragment.newInstance(position)
        else
            throw Exception("!mode")
    }

    override fun getItemCount(): Int {
        return pages.size
    }
}