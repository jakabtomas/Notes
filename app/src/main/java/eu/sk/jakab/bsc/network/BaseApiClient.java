package eu.sk.jakab.bsc.network;

import eu.sk.jakab.bsc.utils.SessionProvider;
import okhttp3.logging.HttpLoggingInterceptor;

public class BaseApiClient {
    private static final Object sInstanceLock = new Object();
    private static ApiClient sINSTANCE = null;

    public static ApiClient getInstance() {
        if (sINSTANCE == null) {
            synchronized (sInstanceLock) {
                if (sINSTANCE == null) {
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    sINSTANCE = new ApiClient();
                    sINSTANCE.getAdapterBuilder().baseUrl(SessionProvider.getInstance().getBaseUrl());
                    sINSTANCE.getOkBuilder()
                            .addInterceptor(loggingInterceptor);
                }
            }
        }
        return sINSTANCE;
    }
}
