package com.hyprful.firstmvvm;

import com.hyprful.firstmvvm.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class PupilApplication extends DaggerApplication {

    private static PupilApplication instance;


    public static synchronized PupilApplication getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
