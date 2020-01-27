package eu.sk.jakab.bsc.base;

import androidx.annotation.StringRes;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    void showError(Throwable e);

    void showToast(@StringRes int stringResourceId);
}
