package com.antipov.redditreader.app;

import com.antipov.redditreader.db.DaoSession;
import com.antipov.redditreader.di.component.AppComponent;

public interface Application {
    AppComponent getComponent();

    DaoSession getDaoSession();
}