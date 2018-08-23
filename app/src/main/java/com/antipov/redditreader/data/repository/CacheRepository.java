package com.antipov.redditreader.data.repository;

import com.antipov.redditreader.db.Cache;

import rx.Observable;

public interface CacheRepository {
    Observable<Cache> getCache();

    Observable setCache(String cached);
}
