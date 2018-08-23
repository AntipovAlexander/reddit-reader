package com.antipov.redditreader.app;

import android.app.Application;

import com.antipov.redditreader.db.DaoMaster;
import com.antipov.redditreader.db.DaoSession;
import com.antipov.redditreader.di.component.AppComponent;
import com.antipov.redditreader.di.component.DaggerAppComponent;
import com.antipov.redditreader.di.module.AppModule;
import com.antipov.redditreader.utils.rx.AppSchedulerProvider;

public class App extends Application implements com.antipov.redditreader.app.Application{

    public AppComponent component;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(this, "cache-db").getWritableDb()).newSession();
    }

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

    @Override
    public DaoSession getDaoSession() {
        return daoSession;
    }


    public void setComponent(AppComponent component) {
        this.component = component;
    }
}
