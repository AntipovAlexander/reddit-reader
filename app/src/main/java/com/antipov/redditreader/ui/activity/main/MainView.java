package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.ui.base.IBaseView;

public interface MainView extends IBaseView {
    void showData(Integer data);

    void showError(String err);
}
