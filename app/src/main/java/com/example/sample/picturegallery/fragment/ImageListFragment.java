package com.example.sample.picturegallery.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import com.example.sample.picturegallery.R;
import com.example.sample.picturegallery.adapter.ImageListAdapter;
import com.example.sample.picturegallery.application.GalleryApplication;
import com.example.sample.picturegallery.model.Image;

public class ImageListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Image> imageList = new ArrayList<>();

    public ImageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        final String id =  getActivity().getIntent().getStringExtra(GalleryItemFragment.ITEM_KEY);
        recyclerView = (RecyclerView) view.findViewById(R.id.imageList);
        imageList = GalleryApplication.getInstance().getItem(id).getListOfImages();
        adapter = new ImageListAdapter(imageList);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
