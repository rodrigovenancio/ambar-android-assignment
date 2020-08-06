package com.git.repolist.data.api;

import java.io.IOException;

import io.appflate.restmock.RESTMockServer;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseUrlChangingInterceptor implements Interceptor {
    private static BaseUrlChangingInterceptor sInterceptor;

    private HttpUrl httpUrl;

    public static BaseUrlChangingInterceptor get() {
        if (sInterceptor == null) {
            sInterceptor = new BaseUrlChangingInterceptor();
        }
        return sInterceptor;
    }

    private BaseUrlChangingInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String mockUrl = RESTMockServer.getUrl();

        if(mockUrl != null) {
            original = original.newBuilder()
                    .url(mockUrl + "/repositories")
                    .build();
        }
        return chain.proceed(original);
    }
}