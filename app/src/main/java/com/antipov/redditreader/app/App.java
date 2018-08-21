package com.antipov.redditreader.app;

import android.app.Application;

import com.antipov.redditreader.di.component.AppComponent;
import com.antipov.redditreader.di.component.DaggerAppComponent;
import com.antipov.redditreader.di.module.AppModule;
import com.antipov.redditreader.utils.rx.AppSchedulerProvider;

public class App extends Application implements com.antipov.redditreader.app.Application{

    public AppComponent component;

    @Override
    public AppComponent getComponent() {
        if (component == null) {
            component = DaggerAppComponent
                    .builder()
                    .appModule(
                            new AppModule(
                                    this,
                                    new AppSchedulerProvider()
                            )
                    ).build();
        }
        return component;
    }

    public void setComponent(AppComponent component) {
        this.component = component;
    }
}
