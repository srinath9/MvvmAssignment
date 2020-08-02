package com.hyprful.firstmvvm.di.module;

import android.app.Application;

import com.hyprful.firstmvvm.DataRepository;
import com.hyprful.firstmvvm.api.repository.PupilServerRepository;
import com.hyprful.firstmvvm.api.repository.PupilServerService;
import com.hyprful.firstmvvm.db.AppDatabase;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hyprful.firstmvvm.Constant.BASE_URL;

@Module
public class AppModule {


    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    @Singleton
    @Provides
    public static PupilServerService providePupilServerService(Retrofit retrofit) {
        return retrofit.create(PupilServerService.class);
    }

    @Provides
    static OkHttpClient provideInterceptor() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .addHeader("X-Request-ID", "d142d449-fac0-41e0-b7a4-b3bd3bc1b569")
                        .addHeader("User-Agent", "Bridge Android Tech Test")

                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient build = httpClient.build();
        return build;
    }

    @Singleton
    @Provides
    public static DataRepository provideDataRepository(AppDatabase appDatabase) {
        DataRepository dataRepository = new DataRepository(appDatabase);
        return dataRepository;
    }

    @Singleton
    @Provides
    public static PupilServerRepository providePupilServerRepository(PupilServerService pupilServerService) {
        PupilServerRepository pupilServerRepository = new PupilServerRepository(pupilServerService);
        return pupilServerRepository;
    }

    @Singleton
    @Provides
    public static AppDatabase provideAppDatabase(Application application) {
        return AppDatabase.getDatabase(application);
    }
}
