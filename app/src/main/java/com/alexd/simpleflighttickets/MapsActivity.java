package com.alexd.simpleflighttickets;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.alexd.simpleflighttickets.Db.DbHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Button mFromCityButton;
    private Button mToCityButton;
    private boolean mIsSelectingFromCity = true;
    private Marker mCityToMarker;
    private Marker mCityFromMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mFromCityButton = (Button) findViewById(R.id.fromButton);
        mToCityButton = (Button) findViewById(R.id.toButton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DbHelper.initializeDatabase();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = addresses != null && addresses.size() > 0 ? addresses.get(0).getLocality() : null;
        if(text == null) return;

        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title(text);

        if(mIsSelectingFromCity) {
            mFromCityButton.setText(text);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            if(mCityFromMarker != null) mCityFromMarker.remove();
            mCityFromMarker = mMap.addMarker(options);
        } else {
            mToCityButton.setText(text);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            if(mCityToMarker != null) mCityToMarker.remove();
            mCityToMarker = mMap.addMarker(options);
        }

    }

    public void FromButtonClick(View view) {
        mIsSelectingFromCity = true;
        mFromCityButton.setBackgroundColor(Color.RED);
        mToCityButton.setBackgroundColor(Color.WHITE);

    }
    public void ToButtonClick(View view){
        mIsSelectingFromCity = false;
        mFromCityButton.setBackgroundColor(Color.WHITE);
        mToCityButton.setBackgroundColor(Color.RED);
    }

    public void GetPriceButtonClick(View view) {
        String cityTo = mToCityButton.getText().toString();
        String cityFrom = mFromCityButton.getText().toString();

        if(cityTo.isEmpty() || cityFrom.isEmpty())
            return;
        Intent intent = new Intent(this, FlightTicketActivity.class);

        intent.putExtra("from", cityFrom);
        intent.putExtra("to", cityTo);

        startActivity(intent);
    }
}
