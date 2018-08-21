package com.antipov.redditreader.ui.activity.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.antipov.redditreader.R;
import com.antipov.redditreader.di.ActivityContext;
import com.antipov.redditreader.di.ApplicationContext;
import com.antipov.redditreader.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter<MainView, MainInteractor> presenter;

    @BindView(R.id.tv_test)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    public void showData(Integer data) {
        textView.setText(data.toString());
    }

    @Override
    public void showError(String err) {

    }
}
