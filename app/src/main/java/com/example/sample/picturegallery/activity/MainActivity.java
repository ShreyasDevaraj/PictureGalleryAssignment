package com.example.sample.picturegallery.activity;

import android.support.v4.app.Fragment;
import com.example.sample.picturegallery.fragment.GalleryItemFragment;

public class MainActivity extends AbstractMainActivity {

    @Override
    protected Fragment createFragment() {
        return new GalleryItemFragment();
    }
}
