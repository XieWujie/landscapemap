package com.example.administrator.landscapemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class BigImageFragment:Fragment(){

    private lateinit var imageView: ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_big_image,container,false)
        imageView = view.findViewById(R.id.imageView)
        val src = activity!!.intent.getIntExtra("imageSrc",0)
        imageView.setImageResource(src)
        return view
    }
}