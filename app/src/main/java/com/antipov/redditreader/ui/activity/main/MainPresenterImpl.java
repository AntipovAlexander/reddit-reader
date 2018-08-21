package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenterImpl <V extends MainView, I extends MainInteractor>
        extends BasePresenter <V, I> implements MainPresenter<V, I> {

    @Inject
    public MainPresenterImpl(I interactor) {
        super(interactor);
    }

    @Override
    public void getData() {
        getInteractor().getData().subscribe(
                integer -> {
                    if (!isViewAttached()) return;
                    getView().showData(integer);
                },
                throwable -> {
                    if (!isViewAttached()) return;
                    getView().showError(throwable.getMessage());
                }
        );
    }
}
