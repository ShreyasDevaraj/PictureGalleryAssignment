package com.example.sample.picturegallery.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;
import com.example.sample.picturegallery.model.Image;

public class ImagePagerAdapter extends PagerAdapter {

    private final List<Image> imageList;
    private final Context context;

    public ImagePagerAdapter(final Context c, final List<Image> list) {
        context = c;
        imageList = list;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageBitmap(imageList.get(position).getBitmap());
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((ImageView) object);
    }
}

