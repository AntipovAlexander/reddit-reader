package com.antipov.redditreader.api;

import com.antipov.redditreader.data.pojo.Top;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Interface with API-endpoints
 *
 * Created by AlexanderAntipov on 21.08.2018.
 */

public interface Api {

    @GET("top.json")
    Observable<Top> getTop(@Query("limit") int limit, @Query("after") String after);
}

