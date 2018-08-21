package com.antipov.redditreader.di.component;

import com.antipov.redditreader.di.module.ActivityModule;
import com.antipov.redditreader.ui.activity.main.MainActivity;

import dagger.Component;

/**
 * Component for Activity inject-targets
 *
 * Created by AlexanderAntipov on 21.08.2018.
 */
@Component(
        dependencies = AppComponent.class, // inheriting dependencies from AppModule.class
        modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
