package org.d3ifcool.jagosholat.views.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.presenters.network.Compass;
import org.d3ifcool.jagosholat.presenters.network.GPSTracker;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.INVISIBLE;

public class KompasFragment extends Fragment {

    // ---------------------------------------------------------------------------------------------
    private static final String TAG = "KompasFragment";
    private float currentAzimuth;
    private Compass compass;
    private ImageView mImageView_Kompas_Jarum, mImageView_Kompas;
    private TextView mTextView_ArahKiblat, mTextview_Lokasi;
    private SharedPreferences prefs;
    private GPSTracker gps;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    // ---------------------------------------------------------------------------------------------

    public KompasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kompas, container, false);
        // -----------------------------------------------------------------------------------------
        compass = new Compass(getContext());
        prefs = getContext().getSharedPreferences("", MODE_PRIVATE);
        gps = new GPSTracker(getContext());
        // -----------------------------------------------------------------------------------------
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipeToRefresh);
        mImageView_Kompas_Jarum = rootView.findViewById(R.id.imageview_kompas_jarum);
        mImageView_Kompas = rootView.findViewById(R.id.imageview_kompas);
        mTextView_ArahKiblat = rootView.findViewById(R.id.textview_arah_kabah);
        mTextview_Lokasi = rootView.findViewById(R.id.textview_lokasi);
        // -----------------------------------------------------------------------------------------
        setupCompass();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetch_GPS(gps, mImageView_Kompas_Jarum, mTextView_ArahKiblat, mTextview_Lokasi);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        return rootView;
    }

    private void setupCompass() {
        getBearing(mTextView_ArahKiblat, mTextview_Lokasi);
        Compass.CompassListener cl = new Compass.CompassListener() {
            @Override
            public void onNewAzimuth(float azimuth) {
                adjustGambarDial(azimuth, mImageView_Kompas);
                adjustArrowQiblat(azimuth);
            }
        };
        compass.setListener(cl);
    }


    public void adjustGambarDial(float azimuth, ImageView kompas) {
        // Log.d(TAG, "will set rotation from " + currentAzimuth + " to "                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = (azimuth);
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        kompas.startAnimation(an);
    }
    public void adjustArrowQiblat(float azimuth) {
        float kiblat_derajat = GetFloat("kiblat_derajat");
        Animation an = new RotateAnimation(-(currentAzimuth)+kiblat_derajat, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = (azimuth);
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        mImageView_Kompas_Jarum.startAnimation(an);
    }

    @SuppressLint("MissingPermission")

    public void getBearing(TextView arahKiblat, TextView lokasi){
        // Get the location manager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        float kiblat_derajat = GetFloat("kiblat_derajat");
        if(kiblat_derajat > 0.0001){
            lokasi.setText("Lokasi anda : \nmenggunakan lokasi terakhir ");
            arahKiblat.setText("Arah Ka'bah : \n" + kiblat_derajat + " derajat dari utara");
        }else {
            fetch_GPS(gps, mImageView_Kompas_Jarum, arahKiblat, lokasi);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                } else {
                    Toast.makeText(getContext(), "This app requires Access Location", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                return;
            }
        }
    }


    public void SaveFloat(String Judul, Float bbb){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putFloat(Judul, bbb);
        edit.apply();
    }

    public Float GetFloat(String Judul){
        return prefs.getFloat(Judul, 0);
    }


    public void fetch_GPS(GPSTracker gps, ImageView jarum, TextView arahKiblat, TextView lokasi){
        double result = 0;
        if(gps.canGetLocation()){
            // -------------------------------------------------------------------------------------
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            lokasi.setText("Lokasi anda : \nLatitude : " + latitude + " & Longitude : " + longitude);
            Log.e("TAG", "GPS is on");
            double lat_saya = gps.getLatitude ();
            double lon_saya = gps.getLongitude ();
            // -------------------------------------------------------------------------------------
            if(lat_saya < 0.001 && lon_saya < 0.001) {
                jarum.setVisibility(INVISIBLE);
                jarum.setVisibility(View.GONE);
                arahKiblat.setText(getContext().getResources().getText(R.string.location_not_found));
                lokasi.setText(getContext().getResources().getText(R.string.location_not_found));

            }else{
                // ---------------------------------------------------------------------------------
                double longitude2 = 39.826206;
                double longitude1 = lon_saya;
                double latitude2 = Math.toRadians(21.422487);
                double latitude1 = Math.toRadians(lat_saya);
                double longDiff= Math.toRadians(longitude2-longitude1);
                double y = Math.sin(longDiff)*Math.cos(latitude2);
                double x = Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);
                // ---------------------------------------------------------------------------------
                result = (Math.toDegrees(Math.atan2(y, x))+360)%360;
                float result2 = (float)result;
                SaveFloat("kiblat_derajat", result2);
                arahKiblat.setText("Arah Ka'bah : \n" + result2 + " derajat dari utara");
                // ---------------------------------------------------------------------------------
            }
        }else{
            gps.showSettingsAlert();
            jarum.setVisibility(View.GONE);
            arahKiblat.setText(getContext().getResources().getText(R.string.enable_location_please));
            lokasi.setText(getContext().getResources().getText(R.string.enable_location_please));
      }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
        //alwaysOn(1);

    }

    @Override
    public void onPause() {
        super.onPause();
        compass.stop();

    }

    @Override
    public void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();

    }


}