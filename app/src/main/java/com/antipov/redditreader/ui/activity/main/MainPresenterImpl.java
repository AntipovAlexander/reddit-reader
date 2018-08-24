package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Top;
import com.antipov.redditreader.ui.base.BasePresenter;
import com.google.gson.Gson;

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
        getInteractor()
                .loadTopPosts(limit)
                .map(top -> {
                    // caching request
                    Gson gson = new Gson();
                    getInteractor().cacheRequest(gson.toJson(top));
                    return top;
                })
                .subscribe(
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
                        resolveCache(throwable);
                    }
                );
    }

    /**
     * Method for resolving: go to offline or display error
     *
     * @param throwable - throwable from observer
     */
    private void resolveCache(Throwable throwable) {
        if (!isViewAttached()) return;
        if (getView().isNetworkConnected()){
            // is network connected, but thrown an error showing error
            getView().showErrorFullScreen(throwable.getMessage());
        } else {
            // if network NOT connected and thrown throwable -
            // it's means that no internet connection - go into offline mode
            getInteractor()
                    .getCachedPage()
                    .subscribe(
                            cache -> {
                                if (!isViewAttached()) return;
                                Gson gson = new Gson();
                                Top cacheModel = gson.fromJson(cache.getJson(), Top.class);
                                getView().renderList(cacheModel.getData().getChildren(), cacheModel.getData().getAfter());
                                getView().notifyOfflineMode();
                            },
                            t -> {
                                if (!isViewAttached()) return;
                                // in case of error returning first throwable error
                                getView().showErrorFullScreen(throwable.getMessage());
                            });

        }
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
        getInteractor()
                .loadNextPage(after, pageSize)
                .map(top -> {
                    // caching request
                    Gson gson = new Gson();
                    getInteractor().cacheRequest(gson.toJson(top));
                    return top;
                })
                .subscribe(
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
                        getView().showMessage(throwable.getMessage());
                        getView().onPaginationError();
                    }
        );
    }
}
