package com.example.vehicletrackingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.appcompat.content.res.AppCompatResources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

// mapbox libraries below
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;
import com.mapbox.geojson.Point;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.Annotation;
//import com.mapbox.maps.plugin.annotation.annotations;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
//import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation;

import java.util.ArrayList;
import java.util.List;

public class TrackVehicle extends AppCompatActivity {

    private MapView mapView;

    FirebaseFirestore db;  // obj for getting data once
    FirebaseFirestore db_for_Realtime;
    Query query;
    ListenerRegistration registration;
    static final String TAG = "Read Data Activity";
    static final String TAG_Realtime = "Read Realtime Data Activity";
    static Bitmap redMarker_Bitmap;
    static Bitmap busMarker_Bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_vehicle);


        redMarker_Bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.red_marker);
        busMarker_Bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.bus);

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        // ------ Adding marker ------
        AnnotationPlugin annotationApi = AnnotationPluginImplKt.getAnnotations(mapView);
        PointAnnotationManager pointAnnotationManager
                = PointAnnotationManagerKt.createPointAnnotationManager(annotationApi, new AnnotationConfig());

        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(68.8191,27.7268))  // Sukkur IBA Coordinates
                .withIconImage(redMarker_Bitmap);

        pointAnnotationManager.create(pointAnnotationOptions);

        // ----------------------------------------------------------------
        PointAnnotationOptions bus_marker = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(68.8150,27.7250))  //
                .withIconImage(busMarker_Bitmap);

        PointAnnotation pointAnnotation = pointAnnotationManager.create(bus_marker);


        db_for_Realtime = FirebaseFirestore.getInstance();
        query = db_for_Realtime.collection("Vehicles");

        registration = query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(TrackVehicle.this,"Failed!!!", Toast.LENGTH_SHORT);
                }
                //List<String> Vehicles = new ArrayList<>();
                //Log.d(TAG,value.toString());
//                        Object obj;
                double longitude=0,latitude=0;
                for(QueryDocumentSnapshot doc: value){


                    //int id;
                    //String title;
                    //id = doc.get("id");
                    Log.d("This is data",doc.getId());
                    //Log.d("This is data",doc.getData().toString());
                    //Log.d("This is data",doc.getLong("id").toString());
                    //Log.d("This is data",doc.getString("title"));
                    Log.d("This is latitude",doc.getString("latitude"));
                    latitude = Double.parseDouble(doc.getString("latitude"));
                    longitude = Double.parseDouble(doc.getString("longitude"));


                    //bus_marker.withPoint(Point.fromLngLat(68.8210,27.7285));
                    //pointAnnotationManager.create(bus_marker);

                }
                pointAnnotation.setPoint(Point.fromLngLat(longitude,latitude));
                pointAnnotationManager.update(pointAnnotation);
            }
        });


        /*
        db = FirebaseFirestore.getInstance();
        db.collection("Vehicles")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(TrackVehicle.this,"Successful", Toast.LENGTH_SHORT);
                                    Log.d(TAG, task.toString());
                                    for(QueryDocumentSnapshot document : task.getResult()){
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                }
                                else{

                                }
                                Log.w(TAG,"Error getting documents.",task.getException());

                            }
                        });

         */

    }  // End of onCreate

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        registration.remove();
    }

}  // End of onEveningTrack