package com.example.sample.picturegallery.model;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;


public class Image implements Parcelable {

    private static final String CAPTION_NAME = "CAPTION_NAME";
    private static final String BITMAP = "BITMAP";
    private final String caption;
    private final Bitmap bitmap;

    public Image(final Bitmap bitmap, final String caption) {
        this.bitmap = bitmap;
        this.caption = caption;
    }

    private Image(final Parcel in) {
        caption = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(final Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(final int size) {
            return new Image[size];
        }
    };

    public String getCaption() {
        return caption;
    }


    public Bitmap getBitmap() {
        return bitmap;
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
        writeBundle.putParcelable(BITMAP, bitmap);
    }
}
