package com.example.administrator.landscapemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.RadioGroup

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*


class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback,OnStreetViewPanoramaReadyCallback{

    private lateinit var mMap: GoogleMap
    private lateinit var group:RadioGroup
    private lateinit var mapFragment:SupportMapFragment
    private lateinit var bigImageFragment: SupportStreetViewPanoramaFragment
    private lateinit var position:LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        group = findViewById(R.id.group)
        mapFragment = SupportMapFragment()
        bigImageFragment = SupportStreetViewPanoramaFragment.newInstance()
        init()
        bigImageFragment.getStreetViewPanoramaAsync(this)
        mapFragment.getMapAsync(this)

    }

    private fun init(){
        val rawX = intent.getDoubleExtra("rawX",0.0)
        val rawY = intent.getDoubleExtra("rawY",0.0)
        position = LatLng(rawX, rawY)
        group.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.map->supportFragmentManager.beginTransaction()
                    .apply {
                        replace(R.id.contentLayout,mapFragment)
                        commit()
                    }
                R.id.big_view->supportFragmentManager.beginTransaction().apply {
                    replace(R.id.contentLayout,bigImageFragment)
                    commit()
                }
            }
        }
        val type = intent.getIntExtra("type",0)
        when(type){
            0->group.check(R.id.map)
            1->group.check(R.id.big_view)
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val rawX = intent.getDoubleExtra("rawX",0.0)
        val rawY = intent.getDoubleExtra("rawY",0.0)
        val name = intent.getIntExtra("imageSrc",0).toString()
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(rawX, rawY)
        mMap.apply {
            addMarker(MarkerOptions().position(sydney).title(name))
            moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))
        }
    }

    override fun onStreetViewPanoramaReady(p0: StreetViewPanorama?) {
        p0?.setPosition(position)
    }

}
