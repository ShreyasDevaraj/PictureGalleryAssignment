package com.example.sample.picturegallery.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import com.example.sample.picturegallery.R;
import com.example.sample.picturegallery.activity.ImageListActivity;
import com.example.sample.picturegallery.adapter.GalleryListAdapter;
import com.example.sample.picturegallery.adapter.ImagePagerAdapter;
import com.example.sample.picturegallery.application.GalleryApplication;
import com.example.sample.picturegallery.listener.RecyclerTouchListener;
import com.example.sample.picturegallery.model.GalleryItem;
import com.example.sample.picturegallery.model.Image;


public class GalleryItemFragment extends Fragment {

    private RecyclerView recyclerView;
    private GalleryListAdapter adapter;
    private RecyclerViewLayoutManager layoutManager;
    private ArrayList<GalleryItem> pictureList = new ArrayList<>();
    private ViewPager viewPager;
    public static final String ITEM_KEY = "ItemId";
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GalleryItemFragment() {
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_galleryitem_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        layoutManager = new RecyclerViewLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(final View view, final int position) {
                final GalleryItem item = pictureList.get(position);
                final Intent intent = new Intent(getActivity(), ImageListActivity.class);
                intent.putExtra(ITEM_KEY, item.getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(final View view, final int position) {
                final GalleryItem item = pictureList.get(position);
                launchViewPager(item);
                layoutManager.setScrollEnabled(false);
            }

            @Override
            public void onRightMovementAfterLongPress() {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }

            @Override
            public void onLeftMovementAfterLongPress() {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }

            @Override
            public void closeViewPager() {
                layoutManager.setScrollEnabled(true);
                viewPager.setVisibility(View.GONE);
            }
        }));

        return view;
    }

    /**
     * Called when all saved state has been restored into the view hierarchy
     * of the fragment.  This can be used to do initialization based on saved
     * state that you are letting the view hierarchy track itself, such as
     * whether check box widgets are currently checked.  This is called
     * after {@link #onActivityCreated(Bundle)} and before
     * {@link #onStart()}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            pictureList = savedInstanceState.getParcelableArrayList("PictureList");
        }
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to { Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        if(pictureList == null || pictureList.isEmpty()) {
            preparePictureList();
        }
        adapter = new GalleryListAdapter(pictureList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        outState.putParcelableArrayList("PictureList", pictureList);
        super.onSaveInstanceState(outState);
    }


    private void launchViewPager(final GalleryItem item) {
        viewPager.setVisibility(View.VISIBLE);
        final ImagePagerAdapter adapter = new ImagePagerAdapter(getActivity(), item.getListOfImages());
        viewPager.setAdapter(adapter);
    }

    private void preparePictureList() {
        final GalleryItem animal = new GalleryItem();
        animal.setCaption(getString(R.string.animal));
        animal.setId("1");
        GalleryApplication.getInstance().addToMap(animal.getId(), animal);

        // Animal List
        animal.setFaceImageId(decodeSampledBitmapFromResource(getResources(), R.drawable.animal1, 300, 300), "1.jpg");
        animal.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.animal2, 300, 300), "2.jpg"));
        animal.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.animal3, 300, 300), "3.jpg"));
        animal.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.animal4, 300, 300), "4.jpg"));
        Bitmap rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.animal5, 300, 300), 90);
        animal.getListOfImages().add(new Image(rotatedBitmap, "5.jpg"));
        animal.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.animal6, 300, 300), "6.jpg"));

        // Architecture list
        final GalleryItem architecture = new GalleryItem();
        architecture.setCaption(getString(R.string.architecture));
        architecture.setId("2");
        GalleryApplication.getInstance().addToMap(architecture.getId(), architecture);
        architecture.setFaceImageId(decodeSampledBitmapFromResource(getResources(), R.drawable.arch1, 300, 300), "1.jpg");
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.arch2, 300, 300), 180);
        architecture.getListOfImages().add(new Image(rotatedBitmap, "2.jpg"));
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.arch3, 300, 300), 180);
        architecture.getListOfImages().add(new Image(rotatedBitmap, "3.jpg"));
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.arch4, 300, 300), 180);
        architecture.getListOfImages().add(new Image(rotatedBitmap, "4.jpg"));

        //      Food list
        final GalleryItem food = new GalleryItem();
        food.setCaption(getString(R.string.food));
        food.setId("3");
        GalleryApplication.getInstance().addToMap(food.getId(), food);
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.food1, 300, 300), 180);

        food.setFaceImageId(rotatedBitmap, "1.jpg");
        food.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.food2, 300, 300), "2.jpg"));

        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.food3, 300, 300), 180);
        food.getListOfImages().add(new Image(rotatedBitmap, "3.jpg"));

        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.food4, 300, 300), 180);
        food.getListOfImages().add(new Image(rotatedBitmap, "4.jpg"));
        food.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.food5, 300, 300), "5.jpg"));

        // Poster List
        final GalleryItem posters = new GalleryItem();
        posters.setCaption(getString(R.string.posters));
        posters.setId("4");
        GalleryApplication.getInstance().addToMap(posters.getId(), posters);
        posters.setFaceImageId(decodeSampledBitmapFromResource(getResources(), R.drawable.posters1, 300, 300), "1.jpg");
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.posters2, 300, 300), 90);
        posters.getListOfImages().add(new Image(rotatedBitmap, "2.jpg"));
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.posters3, 300, 300), 90);
        posters.getListOfImages().add(new Image(rotatedBitmap, "3.jpg"));
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.posters4, 300, 300), 90);
        posters.getListOfImages().add(new Image(rotatedBitmap, "4.jpg"));
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.posters5, 300, 300), 90);
        posters.getListOfImages().add(new Image(rotatedBitmap, "5.jpg"));

        final GalleryItem scenery = new GalleryItem();
        scenery.setCaption(getString(R.string.scenery));
        scenery.setId("5");
        GalleryApplication.getInstance().addToMap(scenery.getId(), scenery);
        scenery.setFaceImageId(decodeSampledBitmapFromResource(getResources(), R.drawable.scenery1, 300, 300), "1.jpg");
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.scenery2, 300, 300), 180);
        scenery.getListOfImages().add(new Image(rotatedBitmap, "2.jpg"));
        scenery.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.scenery3, 300, 300), "3.jpg"));
        rotatedBitmap = rotateBitMap(decodeSampledBitmapFromResource(getResources(), R.drawable.scenery4, 300, 300), 90);
        scenery.getListOfImages().add(new Image(rotatedBitmap, "4.jpg"));
        scenery.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.scenery5, 300, 300), "5.jpg"));
        scenery.getListOfImages().add(new Image(decodeSampledBitmapFromResource(getResources(), R.drawable.scenery6, 300, 300), "6.jpg"));

        pictureList.add(animal);
        pictureList.add(architecture);
        pictureList.add(food);
        pictureList.add(posters);
        pictureList.add(scenery);

    }

    private Bitmap rotateBitMap(final Bitmap bitmap, final int degree) {
        final Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static Bitmap decodeSampledBitmapFromResource(final Resources res, final int resId, final int reqWidth, final int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(final BitmapFactory.Options options, final int reqWidth, final int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private class RecyclerViewLayoutManager extends LinearLayoutManager {

        private boolean isScrollEnabled = true;

        RecyclerViewLayoutManager(final Context context) {
            super(context);
        }

        void setScrollEnabled(final boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            return isScrollEnabled && super.canScrollVertically();
        }
    }
}
