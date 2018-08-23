package com.antipov.redditreader.data.repository;


import com.antipov.redditreader.api.Api;
import com.antipov.redditreader.api.RetrofitUtils;
import com.antipov.redditreader.data.pojo.Top;

import rx.Observable;

/**
 * Implementation of repository with top posts
 */
public class TopPostsRepositoryImpl implements TopPostsRepository {
    /**
     * returns first page with top posts
     * @param limit - page size
     * @return - observable with posts
     */
    @Override
    public Observable<Top> getTopPosts(int limit) {
        return RetrofitUtils.getApi().create(Api.class).getTop(limit, null);
    }

    /**
     * returns next page with top posts (pagination)
     * @param pageSize - page size
     * @return - observable with posts
     */
    @Override
    public Observable<Top> getPageAfter(String after, int pageSize) {
        return RetrofitUtils.getApi().create(Api.class).getTop(pageSize, after);
    }
}
