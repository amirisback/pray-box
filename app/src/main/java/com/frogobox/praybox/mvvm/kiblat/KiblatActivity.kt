package com.frogobox.praybox.mvvm.kiblat

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseActivity
import com.frogobox.praybox.databinding.ActivityKiblatBinding
import com.frogobox.praybox.util.Compass
import com.frogobox.praybox.util.GPSTracker
import kotlinx.android.synthetic.main.activity_kiblat.*

class KiblatActivity : BaseActivity<ActivityKiblatBinding>() {

    private var currentAzimuth = 0f
    private lateinit var compass: Compass
    private lateinit var prefs: SharedPreferences
    private lateinit var gps: GPSTracker


    override fun setupViewBinding(): ActivityKiblatBinding {
        return ActivityKiblatBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        // -----------------------------------------------------------------------------------------
        compass = Compass(this)
        prefs = getSharedPreferences("", Context.MODE_PRIVATE)
        gps = GPSTracker(this)
        // -----------------------------------------------------------------------------------------
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // -----------------------------------------------------------------------------------------
        setupCompass()
        swipeToRefresh.setOnRefreshListener {
            fetch_GPS(gps, imageview_kompas_jarum, textview_arah_kabah, textview_lokasi)
            swipeToRefresh.setRefreshing(false)
        }
    }

    private fun setupCompass() {
        getBearing(textview_arah_kabah, textview_lokasi)
        val cl = Compass.CompassListener { azimuth ->
            adjustGambarDial(azimuth, imageview_kompas)
            adjustArrowQiblat(azimuth)
        }
        compass.setListener(cl)
    }

    private fun adjustGambarDial(azimuth: Float, kompas: ImageView?) { // Log.d(TAG, "will set rotation from " + currentAzimuth + " to "                + azimuth);
        val an: Animation = RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f)
        currentAzimuth = azimuth
        an.duration = 500
        an.repeatCount = 0
        an.fillAfter = true
        kompas!!.startAnimation(an)
    }

    private fun adjustArrowQiblat(azimuth: Float) {
        val kiblat_derajat = GetFloat("kiblat_derajat")
        val an: Animation = RotateAnimation(-currentAzimuth + kiblat_derajat, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f)
        currentAzimuth = azimuth
        an.duration = 500
        an.repeatCount = 0
        an.fillAfter = true
        imageview_kompas_jarum!!.startAnimation(an)
    }

    @SuppressLint("MissingPermission")
    private fun getBearing(arahKiblat: TextView?, lokasi: TextView?) { // Get the location manager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    1)
        }
        val kiblat_derajat = GetFloat("kiblat_derajat")
        if (kiblat_derajat > 0.0001) {
            lokasi!!.text = "Lokasi anda : \nmenggunakan lokasi terakhir "
            arahKiblat!!.text = "Arah Ka'bah : \n$kiblat_derajat derajat dari utara"
        } else {
            fetch_GPS(gps, imageview_kompas_jarum, arahKiblat, lokasi)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // permission was granted, yay! Do the
                } else {
                    Toast.makeText(this, "This app requires Access Location", Toast.LENGTH_LONG).show()
                    finish()
                }
                return
            }
        }
    }

    private fun SaveFloat(Judul: String?, bbb: Float?) {
        val edit = prefs!!.edit()
        edit.putFloat(Judul, bbb!!)
        edit.apply()
    }

    private fun GetFloat(Judul: String?): Float {
        return prefs!!.getFloat(Judul, 0f)
    }

    private fun fetch_GPS(gps: GPSTracker?, jarum: ImageView?, arahKiblat: TextView?, lokasi: TextView?) {
        var result = 0.0
        if (gps!!.canGetLocation()) { // -------------------------------------------------------------------------------------
            val latitude = gps.latitude
            val longitude = gps.longitude
            lokasi!!.text = "Lokasi anda : \nLatitude : $latitude & Longitude : $longitude"
            Log.e("TAG", "GPS is on")
            val lat_saya = gps.latitude
            val lon_saya = gps.longitude
            // -------------------------------------------------------------------------------------
            if (lat_saya < 0.001 && lon_saya < 0.001) {
                jarum!!.visibility = View.INVISIBLE
                jarum.visibility = View.GONE
                arahKiblat!!.text = resources.getText(R.string.location_not_found)
                lokasi.text = resources.getText(R.string.location_not_found)
            } else { // ---------------------------------------------------------------------------------
                val longitude2 = 39.826206
                val latitude2 = Math.toRadians(21.422487)
                val latitude1 = Math.toRadians(lat_saya)
                val longDiff = Math.toRadians(longitude2 - lon_saya)
                val y = Math.sin(longDiff) * Math.cos(latitude2)
                val x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(longDiff)
                // ---------------------------------------------------------------------------------
                result = (Math.toDegrees(Math.atan2(y, x)) + 360) % 360
                val result2 = result.toFloat()
                SaveFloat("kiblat_derajat", result2)
                val tempArahKiblat = "Arah Ka'bah : \n$result2 derajat dari utara"
                arahKiblat!!.text = tempArahKiblat
                // ---------------------------------------------------------------------------------
            }
        } else {
            gps.showSettingsAlert()
            jarum!!.visibility = View.GONE
            arahKiblat!!.text = resources.getText(R.string.enable_location_please)
            lokasi!!.text = resources.getText(R.string.enable_location_please)
        }
    }

    override fun onStart() {
        super.onStart()
        compass.start()
        //alwaysOn(1);
    }

    override fun onPause() {
        super.onPause()
        compass.stop()
    }

    override fun onResume() {
        super.onResume()
        compass.start()
    }

    override fun onStop() {
        super.onStop()
        compass.stop()
    }


}
