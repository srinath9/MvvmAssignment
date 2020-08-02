package com.hyprful.firstmvvm.di.module;

import com.hyprful.firstmvvm.view.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

//    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainActivityModule.class,
            }
    )
    abstract MainActivity contributeMainActivity();

}
