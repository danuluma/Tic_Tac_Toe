package com.dan.tictactoe;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SwipeListener implements OnTouchListener {
    private final GestureDetector gestureDetector;
    private Context context;
//    public SwipeListener(Context context);
//    {
//        gestureDetector = new
//                GestureDetector(context, new GestureListener());
//    }
    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        return gestureDetector.onTouchEvent(event);

    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    public SwipeListener (Context context){
        super();
        this.context = context;
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    private class GestureListener extends SimpleOnGestureListener implements GestureDetector.OnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;


        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean results = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getY() - e1.getY();
                if (Math.abs(diffX)>Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX>0){
                            onSwipeRight();
                        }else {
                            onSwipeLeft();
                        }
                        results = true;

                    }
                }else if (Math.abs(diffY)>SWIPE_THRESHOLD && Math.abs(velocityY)>SWIPE_VELOCITY_THRESHOLD){
                    if (diffY>0){
                        onSwipeBottom();
                    }else {
                        onSwipeTop();
                    }
                    results = true;
                }else if (Math.abs(diffY)<SWIPE_THRESHOLD && Math.abs(velocityY)<SWIPE_VELOCITY_THRESHOLD){

                    onTap();
//                    if (diffY>0){
//                        onSwipeBottom();
//                    }else {
//                        onSwipeTop();
//                    }
                    results = true;
                }

            } catch (Exception e){
                e.printStackTrace();
            }

            return results;
        }
    }

    public void onTap() {
    }

    public void onSwipeTop() {

    }

    public void onSwipeBottom() {

    }

    public void onSwipeRight() {
    }
    public void onSwipeLeft() {
    }
}
