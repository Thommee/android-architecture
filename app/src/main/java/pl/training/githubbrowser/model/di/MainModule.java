package pl.training.githubbrowser.model.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.training.githubbrowser.model.github.AuthorizationHeaderInterceptor;
import pl.training.githubbrowser.model.github.GitHub;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MainModule {

    private Application application;

    public MainModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthorizationHeaderInterceptor())
                .build();
    }

    @Provides
    @Singleton
    GitHub gitHub(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GitHub.class);
    }

}
