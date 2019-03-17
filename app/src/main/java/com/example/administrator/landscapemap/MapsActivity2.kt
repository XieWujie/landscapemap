package com.example.administrator.landscapemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.RadioGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var contentLayout:FrameLayout
    private lateinit var group:RadioGroup
    private lateinit var mapFragment:SupportMapFragment
    private lateinit var bigImageFragment: BigImageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        contentLayout = findViewById(R.id.contentLayout)
        group = findViewById(R.id.group)
        mapFragment = SupportMapFragment()
        bigImageFragment = BigImageFragment()
        mapFragment.getMapAsync(this)
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
        val intent = getIntent()
        val bundle = intent.getBundleExtra("values")
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
}
