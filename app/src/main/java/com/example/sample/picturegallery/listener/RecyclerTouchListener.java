package com.example.sample.picturegallery.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final ClickListener clickListener;
    private boolean isLongClicked = false;
    private float oldX;
    private static final int MOVEMENT_THRESHOLD = 130;

    public RecyclerTouchListener(final Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(final MotionEvent e) {
                final View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null && clickListener != null) {
                    isLongClicked = false;
                    clickListener.onClick(child, recyclerView.getChildPosition(child));
                }
                return true;
            }

            @Override
            public void onLongPress(final MotionEvent e) {
                final View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null && clickListener != null) {
                    oldX = e.getX();
                    isLongClicked = true;
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     * the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isLongClicked = false;
                clickListener.closeViewPager();
                break;
            case MotionEvent.ACTION_MOVE:
                if(isLongClicked) {
                    final float newX = event.getX();
                    if((Math.abs(oldX - newX) > MOVEMENT_THRESHOLD)) {
                        if(newX < oldX) {
                            clickListener.onLeftMovementAfterLongPress();
                        }
                        else if(newX > oldX) {
                            clickListener.onRightMovementAfterLongPress();
                        }
                        oldX = newX;
                    }
                }
                break;

        }
        gestureDetector.onTouchEvent(event);
        return false;
    }

    public interface ClickListener {

        void onClick(View view, int position);

        void onLongClick(View view, int position);

        void onRightMovementAfterLongPress();

        void onLeftMovementAfterLongPress();

        void closeViewPager();
    }
}
