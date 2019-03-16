package com.example.administrator.landscapemap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter:RecyclerView.Adapter<MainHolder>(){

    private val mList = ArrayList<Bean>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_rc_item,parent,false)
        return MainHolder(view)
    }

    fun setList(list:List<Bean>){
        mList.clear()
        mList.addAll(list)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(mList[position])
    }


}