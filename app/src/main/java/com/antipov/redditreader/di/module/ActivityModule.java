package com.antipov.redditreader.di.module;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.antipov.redditreader.ui.activity.main.MainInteractor;
import com.antipov.redditreader.ui.activity.main.MainInteractorImpl;
import com.antipov.redditreader.ui.activity.main.MainPresenter;
import com.antipov.redditreader.ui.activity.main.MainPresenterImpl;
import com.antipov.redditreader.ui.activity.main.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Module for providing Activities dependencies
 *
 * Created by AlexanderAntipov on 21.08.2018.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Provides
    public MainPresenter<MainView, MainInteractor> provideMainPresenter(MainPresenterImpl<MainView, MainInteractor> presenter) {
        return presenter;
    }

    @Provides
    public MainInteractor provideMainInteractor(MainInteractorImpl interactor) {
        return interactor;
    }
}
