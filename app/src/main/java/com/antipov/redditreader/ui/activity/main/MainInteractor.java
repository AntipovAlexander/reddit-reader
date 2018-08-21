package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.ui.base.IBaseInteractor;

import rx.Observable;

public interface MainInteractor extends IBaseInteractor {
    Observable<Integer> getData();
}
