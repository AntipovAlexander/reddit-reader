package com.antipov.redditreader.ui.activity.main;

import com.antipov.redditreader.data.pojo.Child;
import com.antipov.redditreader.ui.base.IBaseView;

import java.util.List;

public interface MainView extends IBaseView {
    void showErrorFullScreen(String err);

    void renderList(List<Child> model, String after);

    void startChromeTab(String url);

    void addItemsToList(List<Child> model, String after, boolean lastPage);

    void removeErrors();

    void onPaginationError();
}
