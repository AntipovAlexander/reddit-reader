package com.antipov.redditreader.di.module;

import android.app.Application;
import android.content.Context;

import com.antipov.redditreader.data.repository.CacheRepository;
import com.antipov.redditreader.data.repository.TopPostsRepository;
import com.antipov.redditreader.data.repository.impl.CacheRepositoryImpl;
import com.antipov.redditreader.data.repository.impl.TopPostsRepositoryImpl;
import com.antipov.redditreader.di.ApplicationContext;
import com.antipov.redditreader.utils.rx.AppSchedulerProvider;
import com.antipov.redditreader.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Module for providing App dependencies
 *
 * Created by AlexanderAntipov on 21.08.2018.
 */

@Module
public class AppModule {

    private final Application mApplication;
    private final AppSchedulerProvider mScheduleProvider;

    public AppModule(Application application, AppSchedulerProvider appSchedulerProvider) {
        this.mScheduleProvider = appSchedulerProvider;
        this.mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return this.mScheduleProvider;
    }

    @Provides
    public TopPostsRepository provideTopPostsRepository() {
        return new TopPostsRepositoryImpl();
    }

    @Provides
    public CacheRepository provideCacheRepository() {
        return new CacheRepositoryImpl(mApplication);
    }
}
