package com.antipov.redditreader.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.antipov.redditreader.R;
import com.antipov.redditreader.ui.activity.main.MainActivity;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;
import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;
import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;

public class SwipeController extends Callback{
    private final int width;
    private boolean swipeBack;
    private int itemPosition;
    private ShareListener shareListener;
    private boolean allowShare;

    public SwipeController(ShareListener shareListener, int width) {
        this.shareListener = shareListener;
        this.width = width;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // swiping items only left and right
        return makeMovementFlags(0, LEFT | RIGHT);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack) {
            if (allowShare) {
                allowShare = false;
                // show share dialog
                shareListener.onShare(itemPosition);
            }
            // returning back
            swipeBack = false;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ACTION_STATE_SWIPE) {
            recyclerView.setOnTouchListener((v, event) -> {
                // get item position
                itemPosition = viewHolder.getAdapterPosition();
                // if you swiped out set flag that we must return it back
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                int swipeRatio = (int) (width / Math.abs(dX));
                // allow share post if swiped out more than 1/3 parts of screen
                allowShare = swipeRatio < 3;
                return false;
            });
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }



    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    public interface ShareListener {
        void onShare(int position);
    }
}
