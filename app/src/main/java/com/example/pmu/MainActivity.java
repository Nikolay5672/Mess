package com.example.pmu;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        fetchData();
    }

    private void fetchData() {
        db.collection("nto100")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Nto> ntoList = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Nto nto = documentSnapshot.toObject(Nto.class);
                            ntoList.add(nto);

                            // Print document content
                            Log.d(TAG, "Document ID: " + documentSnapshot.getId());
                            Log.d(TAG, "Name: " + nto.getName());
                            Log.d(TAG, "Image Path: " + nto.getImgPath());
                            Log.d(TAG, "Place Phone Number: " + nto.getPlacePhoneNumber());
                            Log.d(TAG, "URL Map: " + nto.getUrlMap());
                            Log.d(TAG, "Working Hours: " + nto.getWorkingHours());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failures
                        Log.e(TAG, "Failed to fetch data: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Failed to fetch data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void showGeneral(View view) {
        Intent intent = new Intent(this, GeneralActivity.class);
        startActivity(intent);
    }

    public void showEntityInfo(View view) {
        db.collection("nto100").document("56g7btgx5BLxVJGSgfnZ")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // DocumentSnapshot contains the data
                            String name = documentSnapshot.getString("name");
                            String imgPath = documentSnapshot.getString("imgPath");
                            String placePhoneNumber = documentSnapshot.getString("placePhoneNumber");
                            String urlMap = documentSnapshot.getString("urlMap");
                            String workingHours = documentSnapshot.getString("workingHours");

                            // Start EntityDetailsActivity and pass data
                            Intent intent = new Intent(MainActivity.this, EntityDetailsActivity.class);
                            intent.putExtra("name", name);
                           // intent.putExtra("imgPath", imgPath);
                            intent.putExtra("placePhoneNumber", placePhoneNumber);
                            intent.putExtra("urlMap", urlMap);
                            intent.putExtra("workingHours", workingHours);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle errors
                        Toast.makeText(MainActivity.this, "Failed to fetch data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

