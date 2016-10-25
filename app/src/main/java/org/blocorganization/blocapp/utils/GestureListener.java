package org.blocorganization.blocapp.utils;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public final class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 200;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private LinearLayout menu;
    private View selectedBullet;

    public GestureListener(View rootView, int menuId, int bulletId) {
        this.selectedBullet = rootView.findViewById(bulletId);
        this.menu = (LinearLayout) rootView.findViewById(menuId);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        Log.i("SWIPE_TAG", "Fling Heard");
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                Log.i("SWIPE_TAG", diffX + " Velocity: " + velocityX);
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();

                    } else {
                        onSwipeLeft();

                    }
                }
                result = true;
            } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
            }
            result = true;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public void onSwipeRight() {
        Log.i("SWIPE_TAG", "Right");
        menu.setVisibility(View.VISIBLE);
        if (menu.getChildAt(4) == selectedBullet) {
            menu.removeView(menu.getChildAt(4));
            menu.addView(selectedBullet, 3);
        } else if (menu.getChildAt(3) == selectedBullet){
            menu.removeView(menu.getChildAt(3));
            menu.addView(selectedBullet, 2);
        } else if (menu.getChildAt(2) == selectedBullet) {
            menu.removeView(menu.getChildAt(2));
            menu.addView(selectedBullet, 1);
        } else if (menu.getChildAt(1) == selectedBullet) {
            menu.removeView(menu.getChildAt(1));
            menu.addView(selectedBullet, 0);
        }
    }

    public void onSwipeLeft() {
        Log.i("SWIPE_TAG", "Left");
        menu.setVisibility(View.VISIBLE);
        if (menu.getChildAt(0) == selectedBullet){
            menu.removeView(menu.getChildAt(0));
            menu.addView(selectedBullet, 1);
        } else if (menu.getChildAt(1) == selectedBullet) {
            menu.removeView(menu.getChildAt(1));
            menu.addView(selectedBullet, 2);
        } else if (menu.getChildAt(2) == selectedBullet) {
            menu.removeView(menu.getChildAt(2));
            menu.addView(selectedBullet, 3);
        } else if (menu.getChildAt(3) == selectedBullet && menu.getChildAt(4) != null) {
            menu.removeView(menu.getChildAt(3));
            menu.addView(selectedBullet, 4);
        }
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }

}
