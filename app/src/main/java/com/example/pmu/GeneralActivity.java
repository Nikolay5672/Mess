package com.example.pmu;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GeneralActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView textPictureName;
    private LinearLayout layoutMoreInfo;
    private boolean isMoreInfoVisible = false;
    private FirebaseFirestore db;
    private List<Nto> entities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        viewPager = findViewById(R.id.viewPager);
        textPictureName = findViewById(R.id.textPictureName);
        layoutMoreInfo = findViewById(R.id.layoutMoreInfo);
        setSupportActionBar(findViewById(R.id.toolbar));

        db = FirebaseFirestore.getInstance();

        fetchData();
    }

    private void fetchData() {
        db.collection("nto100")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        entities = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Nto nto = documentSnapshot.toObject(Nto.class);
                            entities.add(nto);
                        }
                        setupViewPager();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failures
                    }
                });
    }

    private void setupViewPager() {
        ImagePagerAdapter adapter = new ImagePagerAdapter(this,entities);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position >= 0 && position < entities.size()) {
                    textPictureName.setText(entities.get(position).getName());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void toggleMoreInfoVisibility() {
        if (isMoreInfoVisible) {
            layoutMoreInfo.setVisibility(View.GONE);
            isMoreInfoVisible = false;
        } else {
            layoutMoreInfo.setVisibility(View.VISIBLE);
            populateMoreInfo(viewPager.getCurrentItem());
            isMoreInfoVisible = true;
        }
    }

    public void openMap(View view) {
        // Implement map opening logic here
    }

    private void populateMoreInfo(int position) {
        TextView textDescription = findViewById(R.id.textDescription);
        TextView textDetailedInfo = findViewById(R.id.textDetailedInfo);

        // Populate additional information for the entity at the given position
        if (position >= 0 && position < entities.size()) {
            Nto entity = entities.get(position);
            String description = "Description: " + entity.getPlacePhoneNumber();
            String detailedInfo = "Additional details:"+ entity.getPlacePhoneNumber();
            textDescription.setText(description);
            textDetailedInfo.setText(detailedInfo);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}






