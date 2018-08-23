package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Top;
import com.antipov.redditreader.db.Cache;
import com.antipov.redditreader.ui.base.IBaseInteractor;

import rx.Observable;

public interface MainInteractor extends IBaseInteractor {
    Observable<Top> loadTopPosts(int limit);

    Observable<Top> loadNextPage(String after, int pageSize);

    void cacheRequest(String request);

    Observable<Cache> getCachedPage();
}
