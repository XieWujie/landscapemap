package com.example.administrator.landscapemap

import android.content.Context

object SourceUtil{

    private val latitude = arrayListOf(22.4195,
    22.4212, 22.4196, 22.4196, 22.4195, 22.4198, 22.4199, 22.4209, 22.421, 22.421)
    private val longitude = arrayListOf(114.2076,
    114.208, 114.2041, 114.2043,114.2044,114.204,114.2074,114.2066,114.2103,114.2103)

    private val images = arrayListOf(R.drawable.image01,R.drawable.image02,R.drawable.image03,
        R.drawable.image04,R.drawable.image05,
        R.drawable.image06,R.drawable.image07,R.drawable.image08,R.drawable.image09,R.drawable.image10)

    fun getSource(context: Context,callback:(list:List<Bean>)->Unit){
        val newList = ArrayList<Bean>()
        val size = Math.min(images.size, Math.min(latitude.size, longitude.size))
        for (i in 0 until size){
            newList.add(Bean(images[i], latitude[i], longitude[i]))
        }
        callback(newList)
    }
}

data class Bean(
    val image:Int,
    val latitude:Double,
    val longitude:Double
)