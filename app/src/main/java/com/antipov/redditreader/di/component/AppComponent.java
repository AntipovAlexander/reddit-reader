package com.antipov.redditreader.di.component;

import android.content.Context;

import com.antipov.redditreader.di.module.AppModule;
import com.antipov.redditreader.di.ApplicationContext;
import com.antipov.redditreader.utils.rx.SchedulerProvider;

import dagger.Component;

/**
 * Created by AlexanderAntipov on 04.06.2018.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
//    void inject(ChangeWallpaperJob changeWallpaperService);
//
//    void inject(ChangeWallPaperForeground changeWallPaperForeground);

    // provide dependencies from app module to dependent components

    @ApplicationContext
    Context context();

//    SharedPrefs sharedPrefs();
//
//    CurrentWallpaperPrefs currentWallpaperPrefs();
//
//    WallPaperSetter wallpaperSetter();
//
    SchedulerProvider schedulerProvider();

}

