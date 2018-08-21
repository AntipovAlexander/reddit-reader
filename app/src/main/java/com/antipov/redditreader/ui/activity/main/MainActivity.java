package com.antipov.redditreader.ui.activity.main;

import android.os.Bundle;
import android.widget.TextView;

import com.antipov.redditreader.R;
import com.antipov.redditreader.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter<MainView, MainInteractor> mPresenter;

    @BindView(R.id.tv_test)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getData();
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
