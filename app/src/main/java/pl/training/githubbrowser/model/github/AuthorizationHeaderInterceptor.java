package pl.training.githubbrowser.model.github;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "token 4bcdb4db092fca6af22aa21702eb36a578fc9b46")
                .build();
        return chain.proceed(request);
    }

}
