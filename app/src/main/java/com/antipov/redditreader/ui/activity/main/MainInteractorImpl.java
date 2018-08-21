package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.ui.base.BaseInteractor;
import com.antipov.redditreader.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import rx.Observable;

public class MainInteractorImpl extends BaseInteractor implements MainInteractor {

    @Inject
    public MainInteractorImpl(SchedulerProvider scheduler) {
        super(scheduler);
    }

    @Override
    public Observable<Integer> getData() {
        return Observable.just(1);
    }
}
