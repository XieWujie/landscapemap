package com.example.administrator.landscapemap

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MainHolder(private val view:View):RecyclerView.ViewHolder(view){

    private val imageView = view.findViewById<ImageView>(R.id.image)
    private val diaList = arrayOf("Show Location On Map","Show StreetView Panorama")
    fun bind(bean: Bean){
        imageView.setImageResource(bean.image)
        view.setOnLongClickListener {
            createDialog(view.context,bean)
            true
        }
    }

    private fun createDialog(context: Context,bean: Bean){
      AlertDialog.Builder(context)
            .setItems(diaList){a,w->
                when(w){
                    0->toMap(context,bean)
                    1->seeBigImage(context,bean.image)
                }
            }.show()
    }

    private fun toMap(context: Context,bean: Bean){
        val intent = Intent(context,MapsActivity2::class.java)
        intent.putExtra("rawX",bean.latitude)
        intent.putExtra("rawY",bean.longitude)
        intent.putExtra("name",bean.image.toString())
        context.startActivity(intent)
    }

    private fun seeBigImage(context: Context,src: Int) {
        val dialog = Dialog(context, R.style.bigImage_dialog_style)
        val p = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val view = ImageView(context)
        view.setImageResource(src)
        view.scaleType = ImageView.ScaleType.FIT_XY
        dialog.setContentView(view, p)
        val window = dialog.window
        window?.setGravity(Gravity.CENTER)
        val layoutParams = window?.attributes
        val displayMetrics = context.resources.displayMetrics
        layoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        window?.decorView?.setPadding(0, 0, 0, 0)
        layoutParams?.height = displayMetrics.heightPixels
        window?.attributes = layoutParams
        dialog.show()
        view.setOnClickListener {
            dialog.dismiss()
        }
    }
}