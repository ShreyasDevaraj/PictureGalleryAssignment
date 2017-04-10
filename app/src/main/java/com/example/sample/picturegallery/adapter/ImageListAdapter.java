package com.example.sample.picturegallery.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.example.sample.picturegallery.R;
import com.example.sample.picturegallery.model.Image;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageHolder> {

    final private List<Image> imageList;

    public ImageListAdapter(final List<Image> list) {
        imageList = list;
    }

    @Override
    public ImageHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_row, parent, false);
        return new ImageHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ImageHolder holder, final int position) {
        final Image item = imageList.get(position);
        holder.imageView.setImageBitmap(item.getBitmap());
        holder.label.setText(item.getCaption());
        holder.label.setTextColor(Color.GRAY);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView label;

        ImageHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageID);
            label = (TextView) itemView.findViewById(R.id.imageName);
        }
    }
}
