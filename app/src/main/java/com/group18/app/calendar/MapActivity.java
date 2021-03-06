package com.group18.app.calendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.group18.app.calendar.database.CommitmentHelper;
import com.group18.app.calendar.database.CommitmentSchema;

import java.text.ParseException;


public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback
{
    private UiSettings mUiSettings;
    private GoogleMap mMap;
    PlaceAutocompleteFragment placeAutoComplete;
    private CommitmentHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map_page);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                mMap.addMarker(
                        new MarkerOptions()
                                .position(place.getLatLng())
                                .title(place.getName().toString())
                );
                mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                        place.getLatLng(), 14.0f)
                );
            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Gainesville
        // and move the map's camera to the same location.
        this.mMap = googleMap;
        mUiSettings = googleMap.getUiSettings();
        mDbHelper = new CommitmentHelper(getApplicationContext());
        mDatabase = mDbHelper.getWritableDatabase();
        Cursor cursor = mDatabase.query(CommitmentSchema.CommitmentTable.NAME,null,
                null, null, null, null, null);
        try {

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String cname = cursor.getString(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.CNAME));
                Double mLat = cursor.getDouble(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.LAT));
                Double mLong = cursor.getDouble(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.LONG));
                if(mLat != 0.0 && mLong != 0.0) {
                    LatLng temp = new LatLng(mLat, mLong);
                    mMap.addMarker(new MarkerOptions().position(temp).title(cname));
                }
                cursor.moveToNext();
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        LatLng gainesville = new LatLng(29.6463, -82.3478);
        mMap.addMarker(new MarkerOptions().position(gainesville)
                .title("Reitz Union"));
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(gainesville, 14.0f) );
        mUiSettings.setZoomControlsEnabled(true);
    }

}
