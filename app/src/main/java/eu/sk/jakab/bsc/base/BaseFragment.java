package eu.sk.jakab.bsc.base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import eu.sk.jakab.bsc.R;
import retrofit2.HttpException;

public class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView<T> {

    protected T presenter;
    private Dialog loadingDialog;

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(final Throwable e) {
        if (isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (e instanceof HttpException) {
                        HttpException httpException = (HttpException) e;
                        Toast.makeText(getContext(), String.valueOf(httpException.code()), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), R.string.oops_something_went_wrong, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        presenter.unsubscribe();
        super.onPause();
    }

    protected void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public T getPresenter() {
        return presenter;
    }

    @Override
    public void showToast(final int stringResourceId) {
        if (isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), stringResourceId, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
