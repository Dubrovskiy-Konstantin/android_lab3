package com.example.mediaplayer

import androidx.fragment.app.Fragment

open class PageFragment(public var file: String) : Fragment() {
    open lateinit var mode : String
}