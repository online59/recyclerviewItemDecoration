package com.example.simpleexpensemanager.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeaderDecoration extends RecyclerView.ItemDecoration {

    private final View mHeaderView;
    private final boolean mSticky;

    public HeaderDecoration(View mHeaderView, boolean mSticky) {
        this.mHeaderView = mHeaderView;
        this.mSticky = mSticky;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int top = parent.getPaddingTop();
        int bottom = mHeaderView.getHeight();

        if (mSticky) {
            View firstVisibleChild = parent.getChildAt(0);
            if (firstVisibleChild != null) {
                int firstVisibleChildTop = firstVisibleChild.getTop();
                if (firstVisibleChildTop < 0) {
                    top = firstVisibleChildTop;
                }
            }
        }

        mHeaderView.layout(parent.getLeft(), top, parent.getHeight(), bottom);
        mHeaderView.draw(c);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mHeaderView.getTop();
        }
    }
}
