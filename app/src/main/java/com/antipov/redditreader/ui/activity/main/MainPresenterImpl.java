package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenterImpl <V extends MainView, I extends MainInteractor>
        extends BasePresenter <V, I> implements MainPresenter<V, I> {

    @Inject
    public MainPresenterImpl(I interactor) {
        super(interactor);
    }

    /**
     * Load first "portion" of posts from backend
     * @param limit - page size
     */
    @Override
    public void loadTopPosts(int limit) {
        if (!isViewAttached()) return;
        getView().showLoadingFullscreen();
        getInteractor().loadTopPosts(limit).subscribe(
                    model -> {
                        // in case of success
                        if (!isViewAttached()) return;
                        getView().hideLoadingFullscreen();
                        // rendering list
                        getView().renderList(model.getData().getChildren(), model.getData().getAfter());
                    },
                    throwable -> {
                        // in case of error
                        if (!isViewAttached()) return;
                        getView().hideLoadingFullscreen();
                        // TODO: implement error handling
                        getView().showError(throwable.getMessage());
                    }
                );
    }

    /**
     * Open ChromeTab with post
     *
     * @param url - link to post
     */
    @Override
    public void onRecyclerClicked(String url) {
        if (!isViewAttached()) return;
        getView().startChromeTab(url);
    }

    /**
     * pagination method
     *
     * @param after - page
     * @param pageSize - page size
     */
    @Override
    public void loadNextPage(String after, int pageSize) {
        if (!isViewAttached()) return;
        getInteractor().loadNextPage(after, pageSize).subscribe(
                model -> {
                    // in case of success
                    if (!isViewAttached()) return;
                    // updating list
                    getView().addItemsToList(
                            model.getData().getChildren(), // new posts
                            model.getData().getAfter(),    // next page
                            model.getData().getAfter() == null); // we have new page or not
                },
                throwable -> {
                    // in case of error
                    if (!isViewAttached()) return;
                    // todo: implement
                }
        );
    }
}
