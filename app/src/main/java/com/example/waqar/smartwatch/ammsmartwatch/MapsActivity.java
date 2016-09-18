package com.example.waqar.smartwatch.ammsmartwatch;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MapsActivity extends Activity implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener {

    /**
     * Overlay that shows a short help text when first launched. It also provides an option to
     * exit the app.
     */
    private DismissOverlayView mDismissOverlay;

    /**
     * The map. It is initialized when the map has been fully loaded and is ready to be used.
     *
     * @see #onMapReady(com.google.android.gms.maps.GoogleMap)
     */
    private GoogleMap mMap;

    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        // Set the layout. It only contains a MapFragment and a DismissOverlay.
        setContentView(R.layout.activity_maps);

        // Retrieve the containers for the root of the layout and the map. Margins will need to be
        // set on them to account for the system window insets.
        final FrameLayout topFrameLayout = (FrameLayout) findViewById(R.id.root_container);
        final FrameLayout mapFrameLayout = (FrameLayout) findViewById(R.id.map_container);

        // Set the system view insets on the containers when they become available.
        topFrameLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                // Call through to super implementation and apply insets
                insets = topFrameLayout.onApplyWindowInsets(insets);

                FrameLayout.LayoutParams params =
                        (FrameLayout.LayoutParams) mapFrameLayout.getLayoutParams();

                // Add Wearable insets to FrameLayout container holding map as margins
                params.setMargins(
                        insets.getSystemWindowInsetLeft(),
                        insets.getSystemWindowInsetTop(),
                        insets.getSystemWindowInsetRight(),
                        insets.getSystemWindowInsetBottom());
                mapFrameLayout.setLayoutParams(params);

                return insets;
            }
        });

        // Obtain the DismissOverlayView and display the introductory help text.
        mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDismissOverlay.setIntroText(R.string.intro_text);
        mDismissOverlay.showIntroIfNecessary();

        // Obtain the MapFragment and set the async listener to be notified when the map is ready.
        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Map is ready to be used.
        //this is a comment
        mMap = googleMap;
        Locations loc = null;

        // Set the long click listener as a way to exit the map.
        mMap.setOnMapLongClickListener(this);

        //get data from previous activity
        Bundle b = this.getIntent().getExtras();
        if (b != null)
            loc=b.getParcelable("MAP_DATA");

        // Add a marker in the place where spectacle location is
        if (loc != null){
            LatLng location = new LatLng(loc.getLat(), loc.getLong());
            mMap.addMarker(new MarkerOptions().position(location).title("Spectacle is at "+loc.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);
            Toast.makeText(getApplicationContext(), "Long click to exit the map and go to spectacle list", Toast.LENGTH_LONG).show();
        }
        //default marker, if spectracle location is not get bt the method, Some error occured.
        else {
            LatLng location = new LatLng(47.2, 30.2);
            mMap.addMarker(new MarkerOptions().position(location).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        }

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // Display the dismiss overlay with a button to exit this activity.
        mDismissOverlay.show();
    }
}
