package eu.sk.jakab.bsc.utils;

import android.content.Context;

public class SessionProvider {
    private static final String URL = "http://private-9aad-note10.apiary-mock.com/";
    private static SessionProvider sINSTANCE = null;
    private Context mContext;

    public static SessionProvider getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new SessionProvider();
        }
        return sINSTANCE;
    }

    public String getBaseUrl() {
        return URL;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
