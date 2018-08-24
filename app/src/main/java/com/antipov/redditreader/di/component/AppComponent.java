package com.antipov.redditreader.di.component;

import com.antipov.redditreader.data.repository.CacheRepository;
import com.antipov.redditreader.data.repository.TopPostsRepository;
import com.antipov.redditreader.di.module.AppModule;
import com.antipov.redditreader.utils.rx.SchedulerProvider;

import dagger.Component;

/**
 * Component for App inject-targets
 *
 * Created by AlexanderAntipov on 21.08.2018.
 */

@Component(modules = AppModule.class)
public interface AppComponent {

    // provide dependencies from app module to dependent components
    SchedulerProvider schedulerProvider();

    TopPostsRepository topPostsRepository();

    CacheRepository cacheRepository();
}

