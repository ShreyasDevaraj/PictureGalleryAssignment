package com.example.sample.picturegallery.application;

import android.app.Application;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import com.example.sample.picturegallery.model.GalleryItem;


public class GalleryApplication extends Application {

    private static WeakReference<GalleryApplication> instance;
    private final Map<String, GalleryItem> map = new HashMap<>();

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance = new WeakReference<>(this);
    }

    public static GalleryApplication getInstance(){
        return instance.get();
    }

    public void addToMap(final String id, final GalleryItem value){
        map.put(id, value);
    }

    public GalleryItem getItem(final String id){
       return map.get(id);
    }
}
