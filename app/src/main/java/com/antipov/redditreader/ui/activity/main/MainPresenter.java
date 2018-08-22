package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.ui.base.IBasePresenter;

public interface MainPresenter <V extends MainView, I extends MainInteractor> extends IBasePresenter<V, I> {
    void loadTopPosts(int limit);

    void onRecyclerClicked(String url);
}
