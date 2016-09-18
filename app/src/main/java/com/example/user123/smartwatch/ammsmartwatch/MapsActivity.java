package com.example.user123.smartwatch.ammsmartwatch;

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

        // layout. so contem MapFragment e DismissOverlay.
        setContentView(R.layout.activity_maps);

        // devolve os containers para a raiz do layout e do mapa.
        // margens precisam de ser postos neles para contabilizar as insercoes da janela do sistema
        final FrameLayout topFrameLayout = (FrameLayout) findViewById(R.id.root_container);
        final FrameLayout mapFrameLayout = (FrameLayout) findViewById(R.id.map_container);

        // Definir a visao do sistema insercoes nos recipientes quando eles se tornam disponiveis.
        topFrameLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                // Chamada até a implementação super e aplicar inserções
                insets = topFrameLayout.onApplyWindowInsets(insets);

                FrameLayout.LayoutParams params =
                        (FrameLayout.LayoutParams) mapFrameLayout.getLayoutParams();

                // Adicionar inserções Wearable para recipiente FrameLayout segurando mapa como margens
                params.setMargins(
                        insets.getSystemWindowInsetLeft(),
                        insets.getSystemWindowInsetTop(),
                        insets.getSystemWindowInsetRight(),
                        insets.getSystemWindowInsetBottom());
                mapFrameLayout.setLayoutParams(params);

                return insets;
            }
        });

        // Obtém o DismissOverlayView  e mostra o texto de ajuda introdutória.
        mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDismissOverlay.setIntroText(R.string.intro_text);
        mDismissOverlay.showIntroIfNecessary();

        // Obtem o MapFragment e poe async listener para ser notificado quando o mapa está pronto.
        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // O mapa esta pronto para ser usado
        //this is a comment
        mMap = googleMap;
        Locations loc = null;

        // Definido listener do clique longo como uma maneira de sair do mapa.
        mMap.setOnMapLongClickListener(this);

        //obtem dados da atividade anterior
        Bundle b = this.getIntent().getExtras();
        if (b != null)
            loc=b.getParcelable("MAP_DATA");

        // Adicionar um marker no lugar onde a localização espetáculo esta
        if (loc != null){
            LatLng location = new LatLng(loc.getLat(), loc.getLong());
            mMap.addMarker(new MarkerOptions().position(location).title("Spectacle is at "+loc.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            // Zoom in, animando a camera.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);
            Toast.makeText(getApplicationContext(), "Long click to exit the map and go to spectacle list", Toast.LENGTH_LONG).show();
        }
        //default marker, if spectracle location is not get bt the method, Some error occured.
        //marcador padrão, se a localização do espetáculo não obtem o método bt, ocorreu algum erro.
        else {
            LatLng location = new LatLng(47.2, 30.2);
            mMap.addMarker(new MarkerOptions().position(location).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        }

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // Display the dismiss overlay with a button to exit this activity.
        // Mostrar o descartar a sobreposição com botao para sair da atividade.
        mDismissOverlay.show();
    }
}
