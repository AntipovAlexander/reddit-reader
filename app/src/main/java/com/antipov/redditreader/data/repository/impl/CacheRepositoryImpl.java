package com.antipov.redditreader.data.repository.impl;

import android.content.Context;

import com.antipov.redditreader.app.App;
import com.antipov.redditreader.data.repository.CacheRepository;
import com.antipov.redditreader.db.Cache;

import java.util.List;

import rx.Observable;

public class CacheRepositoryImpl implements CacheRepository {
    private Context context;

    public CacheRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<Cache> getCache() {
        return Observable.fromCallable(() -> {
            List<Cache> caches = ((App) context).getDaoSession().loadAll(Cache.class);
            if (caches != null) {
                return caches.get(0);
            }
            return null;
        });
    }

    @Override
    public Observable setCache(String cached) {
        return Observable.fromCallable(() -> {
            ((App) context).getDaoSession().deleteAll(Cache.class);
            ((App) context).getDaoSession().insert(new Cache(cached));
            return null;
        });
    }
}
