package com.example.pmu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class EntityDetailsActivity extends AppCompatActivity {
    private List<Nto> ntoList;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_details);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        viewPager = findViewById(R.id.viewPager);

        // Fetch data from Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("nto100")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ntoList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Nto nto = document.toObject(Nto.class);
                            ntoList.add(nto);
                        }

                        // Set up ViewPager with custom adapter
                        EntityPagerAdapter pagerAdapter = new EntityPagerAdapter(this, ntoList);
                        viewPager.setAdapter(pagerAdapter);
                    } else {
                        // Handle errors
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void navigateToLocation(View view) {
        // Get the current position of the ViewPager
        int currentPosition = viewPager.getCurrentItem();

        // Check if the position is valid
        if (currentPosition >= 0 && currentPosition < ntoList.size()) {
            Nto currentEntity = ntoList.get(currentPosition);
            String urlMap = currentEntity.getUrlMap();

            // Proceed with navigating to the location if URL is available
            if (urlMap != null && !urlMap.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMap));
                //intent.setPackage("com.google.android.apps.maps"); // Specify package to ensure opening in Google Maps
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Google Maps app is not installed, handle accordingly
                    Toast.makeText(this, "Google Maps app is not installed", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Handle case where URL is not available
                Toast.makeText(this, "URL not available", Toast.LENGTH_SHORT).show();
            }
        }
    }
}




