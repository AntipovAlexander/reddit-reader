package com.antipov.redditreader.di.component;

import com.antipov.redditreader.di.module.ActivityModule;
import com.antipov.redditreader.ui.activity.main.MainActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
