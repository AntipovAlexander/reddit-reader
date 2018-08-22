package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Top;
import com.antipov.redditreader.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenterImpl <V extends MainView, I extends MainInteractor>
        extends BasePresenter <V, I> implements MainPresenter<V, I> {

    private Top model;

    @Inject
    public MainPresenterImpl(I interactor) {
        super(interactor);
    }

    @Override
    public void loadTopPosts(int limit) {
        if (!isViewAttached()) return;
        getView().showLoadingFullscreen();
        getInteractor().loadTopPosts(limit).subscribe(
                    model -> {
                        this.model = model;
                        if (!isViewAttached()) return;
                        getView().hideLoadingFullscreen();
                        getView().renderList(model.getData().getChildren(), model.getData().getAfter());
                    },
                    throwable -> {
                        if (!isViewAttached()) return;
                        getView().hideLoadingFullscreen();
                        getView().showError(throwable.getMessage());
                    }
                );
    }

    @Override
    public void onRecyclerClicked(String url) {
        if (!isViewAttached()) return;
        getView().startChromeTab(url);
    }

    @Override
    public void loadNextPage(String after, int pageSize) {
        if (!isViewAttached()) return;
        getInteractor().loadNextPage(after, pageSize).subscribe(
                model -> {
                    if (!isViewAttached()) return;
                    getView().addItemsToList(
                            model.getData().getChildren(),
                            model.getData().getAfter(),
                            model.getData().getAfter() == null);
                },
                throwable -> {
                    if (!isViewAttached()) return;
                }
        );
    }
}
