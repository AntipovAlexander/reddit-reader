package com.antipov.redditreader.di.module;

import android.app.Application;
import android.content.Context;

import com.antipov.redditreader.di.ApplicationContext;
import com.antipov.redditreader.utils.rx.AppSchedulerProvider;
import com.antipov.redditreader.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AlexanderAntipov on 04.06.2018.
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


//    @Provides
//    public SharedPrefs provideSharedPrefs() {
//        return new SharedPrefs(mApplication);
//    }
//
//    @Provides
//    public CurrentWallpaperPrefs provideCurrentWallpaperPrefs() {
//        return new CurrentWallpaperPrefs(mApplication);
//    }
//
//    @Provides
//    public WallPaperSetter provideWallPaperSetter() {
//        return new WallPaperSetter(mApplication);
//    }
}
