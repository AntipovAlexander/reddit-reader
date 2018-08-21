package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Top;
import com.antipov.redditreader.data.repository.TopPostsRepository;
import com.antipov.redditreader.ui.base.BaseInteractor;
import com.antipov.redditreader.utils.common.Const;
import com.antipov.redditreader.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import rx.Observable;

public class MainInteractorImpl extends BaseInteractor implements MainInteractor {

    private final TopPostsRepository repository;

    @Inject
    public MainInteractorImpl(SchedulerProvider scheduler, TopPostsRepository repository) {
        super(scheduler);
        this.repository = repository;
    }

    @Override
    public Observable<Top> loadTopPosts(int limit) {
        return  repository
                .getTopPosts(limit)
                .subscribeOn(newThread())
                .observeOn(ui())
                .retry(Const.RETRY_COUNT);
    }
}
