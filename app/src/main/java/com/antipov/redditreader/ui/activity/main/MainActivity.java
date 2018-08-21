package com.antipov.redditreader.ui.activity.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.antipov.redditreader.R;
import com.antipov.redditreader.data.pojo.Child;
import com.antipov.redditreader.ui.adapter.TopAdapter;
import com.antipov.redditreader.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter<MainView, MainInteractor> presenter;

    @BindView(R.id.fl_progress) FrameLayout progress;
    @BindView(R.id.rv_tops) RecyclerView recyclerTop;

    private TopAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        presenter.attachView(this);
        presenter.loadTopPosts(10);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void showLoadingFullscreen() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingFullscreen() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void renderList(List<Child> model) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerTop.setLayoutManager(mLayoutManager);
        mAdapter = new TopAdapter(this, model);
        recyclerTop.setLayoutManager(mLayoutManager);
        recyclerTop.setAdapter(mAdapter);
    }

    @Override
    public void showError(String err) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
