package com.example.pmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Nto> mImageResources;

    public ImagePagerAdapter(Context context, List<Nto> imageResources) {
        mContext = context;
        mImageResources = imageResources;
    }


    /*public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(mImageResources.get(position));
        container.addView(view);
        return view;
    }*/

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImageResources.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}

