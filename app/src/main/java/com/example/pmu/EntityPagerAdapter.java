package com.example.pmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class EntityPagerAdapter extends PagerAdapter {
    private List<Nto> ntoList;
    private Context context;

    public EntityPagerAdapter(Context context, List<Nto> ntoList) {
        this.context = context;
        this.ntoList = ntoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_entity, container, false);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView imgPathTextView = view.findViewById(R.id.imgPathTextView);
        TextView placePhoneNumberTextView = view.findViewById(R.id.placePhoneNumberTextView);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView urlMapTextView = view.findViewById(R.id.urlMapTextView);
        TextView workingHoursTextView = view.findViewById(R.id.workingHoursTextView);
        TextView distanceTextView = view.findViewById(R.id.distanceTextView);

        Nto entity = ntoList.get(position);

        nameTextView.setText(entity.getName());
        imgPathTextView.setText(entity.getImgPath());
        placePhoneNumberTextView.setText(entity.getPlacePhoneNumber());
        urlMapTextView.setText(entity.getUrlMap());
        workingHoursTextView.setText(entity.getWorkingHours());
        distanceTextView.setText(String.valueOf(entity.getDistance()));

        Glide.with(context)
                .load(entity.getImgPath())
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return ntoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

