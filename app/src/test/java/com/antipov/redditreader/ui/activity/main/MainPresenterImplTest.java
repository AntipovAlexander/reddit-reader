package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Top;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Observable;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest {

    @Mock
    MainView mockedView;

    @Mock
    MainInteractor mockedInteractor;

    MainPresenter<MainView, MainInteractor> presenter;

    @Before
    public void setUp(){
        presenter = new MainPresenterImpl<>(mockedInteractor);
        presenter.attachView(mockedView);
    }

    /**
     * method to test positive scenario with mocked interactor
     */
    @Test
    public void loadTopPostsPositive() {
        int limit = 10;

        Mockito.doReturn(Observable.just(Top.getForTest()))
                .when(mockedInteractor)
                .loadTopPosts(limit);
        presenter.loadTopPosts(limit);
        Mockito.verify(mockedView).showLoadingFullscreen();
        Mockito.verify(mockedView).renderList(ArgumentMatchers.anyList(), ArgumentMatchers.anyString());
        Mockito.verify(mockedView).hideLoadingFullscreen();
        Mockito.verifyNoMoreInteractions(mockedView);
    }

    /**
     * method to test negative scenario with mocked interactor
     */
    @Test
    public void loadTopPostsNegative() {
        int limit = 10;

        Mockito.doReturn(Observable.just(new Throwable()))
                .when(mockedInteractor)
                .loadTopPosts(limit);
        presenter.loadTopPosts(limit);
        Mockito.verify(mockedView).showLoadingFullscreen();
        Mockito.verify(mockedView).showErrorFullScreen(ArgumentMatchers.anyString());
        Mockito.verify(mockedView).hideLoadingFullscreen();
        Mockito.verifyNoMoreInteractions(mockedView);
    }

    @Test
    public void onRecyclerClicked() {
        String url = "https://www.google.com/";
        presenter.onRecyclerClicked(url);
        Mockito.verify(mockedView).startChromeTab(url);
        Mockito.verifyNoMoreInteractions(mockedView);
    }
}