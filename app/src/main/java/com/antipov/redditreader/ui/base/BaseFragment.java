package com.antipov.redditreader.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.antipov.redditreader.R;
import com.antipov.redditreader.di.component.ActivityComponent;

/**
 * Base class for fragments.
 *
 * Extends:
 * @see Fragment - Base class for fragments
 * features.
 *
 * Implements:
 * @see IBaseView - interaface for all views in app
 *
 * @author AlexanderAntipov
 * @since 21.08.2018.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {
    private BaseActivity mActivity;
    private MaterialDialog materialDialog;
    private Context context;
    private View mView;

    /**
     * Saving context and reference to base activity when attaching fragment to activity
     * @param context - context of attached activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    /**
     * Inflating view with layoutId and calling base methods.
     * @see #getLayoutId()
     * @see #getExtras()
     * @see #initViews()
     * @see #initListeners()
     *
     * @param inflater - layout inflater
     * @param container - container
     * @param savedInstanceState - saved state
     * @return inflated View.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
        }

        getExtras();
        initViews();
        initListeners();
        return mView;
    }

    /**
     * @return base activity reference
     */
    @Nullable
    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    /**
     * show progress dialog
     */
    @Override
    public void showLoading() {
        hideLoading();
        materialDialog = new MaterialDialog.Builder(context)
                .title(R.string.progress_dialog_title)
                .content(R.string.please_wait)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    /**
     * hide progress dialog
     */
    @Override
    public void hideLoading() {
        if (materialDialog != null && materialDialog.isShowing()) {
            materialDialog.cancel();
        }
    }

    /**
     * Show snack bar with error text
     * Calling parent activity method.
     * @see #getBaseActivity()
     * @see BaseActivity#onError(String)
     *
     * @param message - String for message.
     */
    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    /**
     * Show snack bar with error text
     * Calling parent activity method.
     * @see #getBaseActivity()
     * @see BaseActivity#onError(int)
     *
     * @param resId - String id for message. I.e. R.string.network_error
     */
    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    /**
     * Show custom message with String
     * Calling parent activity method.
     * @see #getBaseActivity()
     * @see BaseActivity#showMessage(String)
     *
     * @param message - your string message
     */
    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    /**
     * Show custom message with String
     * Calling parent activity method.
     * @see #getBaseActivity()
     * @see BaseActivity#showMessage(int)
     *
     * @param resId - String id for message. I.e. R.string.greetings
     */
    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    /**
     * Utility method for checking Internet connection
     *
     * @return connected or not.
     */
    @Override
    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }


    /**
     * Calling when detach fragment from activty
     */
    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    /**
     * Hiding virtual keyboard
     */
    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    /**
     * Instantiate and return activity component
     *
     * @return ActivityComponent generated by Dagger2
     */
    @Nullable
    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getComponent();
        }
        return null;
    }

    /**
     * Method for setup layout.
     * An implementation returning layout id for inflating.
     * I.e. R.layout.main
     *
     * @return layout id.
     */
    public abstract int getLayoutId();

    /**
     * Get extras from bundle
     */
    public abstract void getExtras();

    /**
     * Initializing views. Put in the implementation your findViewById() methods
     * Or instantiate ButterKnife instead.
     */
    public abstract void initViews();

    /**
     * Create your listener callbacks there.
     */
    public abstract void initListeners();
}
