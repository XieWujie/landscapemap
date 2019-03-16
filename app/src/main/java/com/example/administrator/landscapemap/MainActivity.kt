package com.example.administrator.landscapemap

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        title ="CU Sweetspot"
        init()
    }

    private fun init(){
        val gridLayoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        SourceUtil.getSource(this){
            adapter.setList(it)
            runOnUiThread{
                adapter.notifyDataSetChanged()
            }
        }
    }
}
