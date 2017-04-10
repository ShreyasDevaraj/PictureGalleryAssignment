package com.example.sample.picturegallery.activity;

import android.support.v4.app.Fragment;
import com.example.sample.picturegallery.fragment.ImageListFragment;

public class ImageListActivity extends AbstractMainActivity {

    @Override
    protected Fragment createFragment() {
        return new ImageListFragment();
    }
}
