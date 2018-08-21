package com.antipov.redditreader.data.repository;


import com.antipov.redditreader.api.Api;
import com.antipov.redditreader.api.RetrofitUtils;
import com.antipov.redditreader.data.pojo.Top;

import rx.Observable;

public class TopPostsRepository {
    public Observable<Top> getTopPosts(int limit) {
        return RetrofitUtils.getApi().create(Api.class).getTop(limit);
    }

}
