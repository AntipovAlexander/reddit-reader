package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Child;
import com.antipov.redditreader.ui.base.IBaseView;

import java.util.List;

public interface MainView extends IBaseView {
    void showError(String err);

    void renderList(List<Child> model);

    void startChromeTab(String url);
}
