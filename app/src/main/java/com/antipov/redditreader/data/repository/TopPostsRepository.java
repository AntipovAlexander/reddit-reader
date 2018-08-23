package com.antipov.redditreader.data.repository;

import com.antipov.redditreader.data.pojo.Top;

import rx.Observable;

/**
 * Repository with top posts
 */
public interface TopPostsRepository {
    Observable<Top> getTopPosts(int limit);

    Observable<Top> getPageAfter(String after, int pageSize);
}
