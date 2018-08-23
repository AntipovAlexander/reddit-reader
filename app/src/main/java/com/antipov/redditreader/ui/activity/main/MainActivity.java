package com.antipov.redditreader.ui.activity.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antipov.redditreader.R;
import com.antipov.redditreader.app.App;
import com.antipov.redditreader.data.pojo.Child;
import com.antipov.redditreader.db.Cache;
import com.antipov.redditreader.ui.adapter.TopAdapter;
import com.antipov.redditreader.ui.base.BaseActivity;
import com.antipov.redditreader.utils.GlideApp;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.antipov.redditreader.utils.common.Const.PAGE_SIZE;

public class MainActivity extends BaseActivity implements MainView, TopAdapter.TopAdapterListener {

    @Inject
    MainPresenter<MainView, MainInteractor> presenter;

    @BindView(R.id.fl_progress) FrameLayout progress;
    @BindView(R.id.rv_tops) RecyclerView recyclerTop;
    @BindView(R.id.error_layout) RelativeLayout errorLayout;
    @BindView(R.id.tv_error_text) TextView errorMessageText;
    @BindView(R.id.btn_try_again) Button tryAgain;
    @BindView(R.id.srl_swipe_refresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private TopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remove theme with splash screen image
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        presenter.attachView(this);
        initAdapter();
        presenter.loadTopPosts(PAGE_SIZE);
    }

    private void initAdapter() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerTop.setLayoutManager(mLayoutManager);
        adapter = new TopAdapter(this, this, GlideApp.with(this));
        recyclerTop.setLayoutManager(mLayoutManager);
        recyclerTop.setAdapter(adapter);
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
        tryAgain.setOnClickListener(l -> {
            removeErrors();
            presenter.loadTopPosts(PAGE_SIZE);
        });
        refreshLayout.setOnRefreshListener(() -> {
            presenter.loadTopPosts(PAGE_SIZE);
        });
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() !=null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }
    }

    @Override
    public void showLoadingFullscreen() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingFullscreen() {
        progress.setVisibility(View.GONE);
    }

    /**
     * method for displaying top posts list
     * @param model - data
     * @param after - next page
     */
    @Override
    public void renderList(List<Child> model, String after) {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        adapter.putNewData(model, after);
    }

    /**
     * method for showing full screen error
     *
     * @param err - displayed error
     */
    @Override
    public void showErrorFullScreen(String err) {
        errorLayout.setVisibility(View.VISIBLE);
        errorMessageText.setText(err);
    }

    /**
     * method for removing error messages from screen if it displayed
     */
    @Override
    public void removeErrors() {
        if (errorLayout.getVisibility() == View.VISIBLE) {
            errorLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPaginationError() {
        adapter.onPaginationError();
    }

    @Override
    public void onRecyclerItemClicked(String url) {
        presenter.onRecyclerClicked(url);
    }

    @Override
    public void onNextPageRequired(String after) {
        presenter.loadNextPage(after, PAGE_SIZE);
    }

    /**
     * method for opening top post in new Chrome Tab
     * @param url - url to post
     */
    @Override
    public void startChromeTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.primaryColor));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    /**
     * Adding new page to list
     *
     * @param model - data
     * @param after - next page
     * @param lastPage - is this last page or not
     */
    @Override
    public void addItemsToList(List<Child> model, String after, boolean lastPage) {
        adapter.addItems(model, after, lastPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
