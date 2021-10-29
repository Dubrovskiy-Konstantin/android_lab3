package com.example.mediaplayer

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.fragment.app.Fragment
import android.provider.MediaStore




open class PageFragment(public var file: String) : Fragment() {
    open lateinit var mode : String

    open fun getFileNameFromURI(contentUri: Uri): String? {
        var path: String? = null
        val proj = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor: Cursor? = context?.getContentResolver()?.query(contentUri, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            path = cursor.getString(column_index)
        }
        cursor.close()
        var tmp = path?.split('/')
        return tmp?.get(tmp.lastIndex)
    }
}