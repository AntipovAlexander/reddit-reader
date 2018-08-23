package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Top;
import com.antipov.redditreader.data.repository.CacheRepository;
import com.antipov.redditreader.data.repository.TopPostsRepository;
import com.antipov.redditreader.db.Cache;
import com.antipov.redditreader.ui.base.BaseInteractor;
import com.antipov.redditreader.utils.common.Const;
import com.antipov.redditreader.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import rx.Observable;

public class MainInteractorImpl extends BaseInteractor implements MainInteractor {

    private final TopPostsRepository postsRepository;
    private final CacheRepository cacheRepository;

    @Inject
    public MainInteractorImpl(
            SchedulerProvider scheduler,
            TopPostsRepository postsRepository,
            CacheRepository cacheRepository) {

        super(scheduler);
        this.cacheRepository = cacheRepository;
        this.postsRepository = postsRepository;

    }

    @Override
    public Observable<Top> loadTopPosts(int limit) {
        return  postsRepository
                .getTopPosts(limit)
                .subscribeOn(newThread())
                .observeOn(ui())
                .retry(Const.RETRY_COUNT);
    }

    @Override
    public Observable<Top> loadNextPage(String after, int pageSize) {
        return  postsRepository
                .getPageAfter(after, pageSize)
                .subscribeOn(newThread())
                .observeOn(ui())
                .retry(Const.RETRY_COUNT);
    }

    @Override
    public void cacheRequest(String request) {
        cacheRepository
                .setCache(request)
                .subscribeOn(newThread())
                .observeOn(ui())
                .subscribe();
    }

    @Override
    public Observable<Cache> getCachedPage() {
        return cacheRepository
                .getCache()
                .subscribeOn(newThread())
                .observeOn(ui());
    }
}
