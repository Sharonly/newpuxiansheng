package com.puxiansheng.www.ui.order.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.puxiansheng.util.ext.MapUtil
import com.puxiansheng.www.databinding.DialogSelectMapBinding
import kotlinx.android.synthetic.main.dialog_select_map.*


class MapSelectDialog(var lat: Double, var lng: Double, var desc: String) : DialogFragment() {
    private lateinit var binding: DialogSelectMapBinding
    private var mapCount = 0
    private var sLat: Double = 0.0
    private var sLng: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.BOTTOM)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogSelectMapBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
        getLngAndLat(requireContext())
        var maplist = MapUtil().hasMap(context)
        for (i in maplist.indices) {
            if (maplist.get(i).contains("com.autonavi.minimap")) {
                btGaode.visibility = View.VISIBLE
                line1.visibility = View.VISIBLE
                mapCount++
            } else if (maplist.get(i).contains("com.baidu.BaiduMap")) {
                btBaidu.visibility = View.VISIBLE
                line3.visibility = View.VISIBLE
                mapCount++
            } else if (maplist.get(i).contains("com.tencent.map")) {
                btTencnet.visibility = View.VISIBLE
                line2.visibility = View.VISIBLE
                mapCount++
            }
        }

        if (mapCount == 0) {
            btNo.visibility = View.VISIBLE
            line4.visibility = View.VISIBLE
        }

        binding.btGaode.setOnClickListener {
            var intent = Intent(
                "android.intent.action.VIEW",
                android.net.Uri.parse("androidamap://route?sourceApplication=appName&slat=" + sLat + "&slon=" + sLng + "&sname=我的位置&dlat=" + lat + "&dlon=" + lng + "&dname=" + desc + "&dev=0&t=2")
            )
            context?.startActivity(intent)
            dismiss()
        }


        binding.btTencnet.setOnClickListener {
            var intent = Intent(
                "android.intent.action.VIEW",
                android.net.Uri.parse("qqmap://map/routeplan?type=drive&from=&fromcoord=&to=" + desc + "&tocoord=" + lat + "," + lng + "&policy=0&referer=appName")
            )
            context?.startActivity(intent)
            dismiss()
        }

        btBaidu.setOnClickListener {
            var intent = Intent(
                "android.intent.action.VIEW",
                android.net.Uri.parse("baidumap://map/geocoder?location=" + lat + "," + lng)
            )
            context?.startActivity(intent)
            dismiss()
        }

        btNo.setOnClickListener {
            Toast.makeText(requireActivity(), "", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.btCancel.setOnClickListener { dismiss() }

    }.root

    @SuppressLint("MissingPermission")
    fun getLngAndLat(context: Context) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.allProviders
        for (provider in providers) {
            val location = locationManager.getLastKnownLocation(provider)
            if (location != null) {
                sLat = location.latitude
                sLng = location.longitude

            }
        }
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000,
            0F,
            locationListener
        )
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) {
            sLat = location.latitude
            sLng = location.longitude
        }

    }


    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
                sLat = location.latitude
                sLng = location.longitude
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
    }


}