package com.example.sample.picturegallery.model;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;


public class GalleryItem implements Parcelable {

    private static final String CAPTION_NAME = "CAPTION_NAME";
    private static final String ID = "ID";
    private static final String FACE_IMAGE = "FACE_IMAGE";
    private static final String LIST_OF_IMAGE = "LIST_OF_IMAGE";
    private String caption;
    private String id;
    private Bitmap faceImageId;
    private ArrayList<Image> listOfImages = new ArrayList<>();

    private GalleryItem(final Parcel in) {
        caption = in.readString();
        id = in.readString();
        faceImageId = in.readParcelable(Bitmap.class.getClassLoader());
        listOfImages = in.createTypedArrayList(Image.CREATOR);
    }

    public GalleryItem() {

    }

    public static final Creator<GalleryItem> CREATOR = new Creator<GalleryItem>() {
        @Override
        public GalleryItem createFromParcel(Parcel in) {
            return new GalleryItem(in);
        }

        @Override
        public GalleryItem[] newArray(int size) {
            return new GalleryItem[size];
        }
    };

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Bitmap getFaceImageId() {
        return faceImageId;
    }

    public void setFaceImageId(final Bitmap faceImageId, String name) {
        this.faceImageId = faceImageId;
        listOfImages.add(new Image(faceImageId, name));
    }

    public List<Image> getListOfImages() {
        return listOfImages;
    }


    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     * May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        final Bundle writeBundle = new Bundle();
        writeBundle.putString(CAPTION_NAME, caption);
        writeBundle.putString(ID, id);
        writeBundle.putParcelable(FACE_IMAGE, faceImageId);
        writeBundle.putParcelableArrayList(LIST_OF_IMAGE, listOfImages);
    }
}
