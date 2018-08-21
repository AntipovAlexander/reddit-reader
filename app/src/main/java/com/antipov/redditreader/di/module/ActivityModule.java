package com.antipov.redditreader.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.antipov.redditreader.di.ActivityContext;
import com.antipov.redditreader.ui.activity.main.MainInteractor;
import com.antipov.redditreader.ui.activity.main.MainInteractorImpl;
import com.antipov.redditreader.ui.activity.main.MainPresenter;
import com.antipov.redditreader.ui.activity.main.MainPresenterImpl;
import com.antipov.redditreader.ui.activity.main.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    Activity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideActivityContext(){
        return activity;
    }


    @Provides
    public MainPresenter<MainView, MainInteractor> provideMainPresenter(MainPresenterImpl<MainView, MainInteractor> presenter) {
        return presenter;
    }

    @Provides
    public MainInteractor provideMainInteractor(MainInteractorImpl interactor) {
        return interactor;
    }

//    @Provides
//    JobUtils provideJobUtils() {
//        return new JobUtils(new Version(), activity);
//    }

//    @Provides
//    WallpaperTargetDialog provideWallpaperTargetDialog(){
//        return new WallpaperTargetDialog(activity);
//    }

//    @Provides
//    public MainPresenterImpl<MainView, MainInteractor> provideMainPresenter(MainPresenterImpl<MainView, MainInteractor> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    public PhotoDetailPresenter<PhotoDetailView, PhotoDetailInteractor> providerPhotoDetailPresenter(
//            PhotoDetailPresenterImpl<PhotoDetailView, PhotoDetailInteractor> presenter
//    ) {
//        return presenter;
//    }
//
//    @Provides
//    public SchedulerFragmentPresenter<SchedulerFragmentView, SchedulerFragmentInteractor> providerSchedulerFragmentPresenter(
//            SchedulerFragmentPresenterImpl<SchedulerFragmentView, SchedulerFragmentInteractor> presenter
//    ) {
//        return presenter;
//    }
//
//    @Provides
//    public MainInteractor provideMainInteractor(MainInteractorImpl interactor) {
//        return interactor;
//    }
//
//    @Provides
//    public PhotoDetailInteractor providePhotoDetailInteractor(PhotoDetailInteractorImpl interactor) {
//        return interactor;
//    }
//
//    @Provides
//    public SchedulerFragmentInteractor provideSchedulerFragmentInteractor(SchedulerFragmentInteractorImpl interactor) {
//        return interactor;
//    }
}
