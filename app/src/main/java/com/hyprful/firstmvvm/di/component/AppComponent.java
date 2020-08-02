package com.hyprful.firstmvvm.di.component;

import android.app.Application;

import com.hyprful.firstmvvm.PupilApplication;
import com.hyprful.firstmvvm.di.module.ActivityModule;
import com.hyprful.firstmvvm.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
    })
public interface AppComponent  extends AndroidInjector<PupilApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }



}
