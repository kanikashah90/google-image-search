package com.kanikash.gridimagesearch.models;

import android.widget.AbsListView;

/**
 * Created by kanikash on 2/1/15.
 */
public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    private int visibleThreshold = 4;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    public EndlessScrollListener() {
    }

    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public EndlessScrollListener(int visibleThreshold, int startPage) {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // Case 1. if totalItemCount < previousTotalItemCount
        if(totalItemCount < this.previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) { this.loading = true; }
        }

        // Case 2. loading finish state
        if(loading && (totalItemCount > this.previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }

        // Case 3. If we need more items to load
        if(!loading && ((totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) ) {
            onLoadMore(currentPage+1, totalItemCount);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemCount);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
