package com.hyprful.firstmvvm.di.module;

import com.hyprful.firstmvvm.background.PupilService;
import com.hyprful.firstmvvm.view.fragment.MainFragment;
import com.hyprful.firstmvvm.view.fragment.NewPupilFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {


    @ContributesAndroidInjector(
            modules = {
                    MainFragmentModule.class,
            }
    )
    public abstract MainFragment bindMainFragment();

    @ContributesAndroidInjector(
            modules = {
                    NewPupilModule.class,
            }
    )
    public abstract NewPupilFragment bindNewPupilFragment();


    @ContributesAndroidInjector()
    public abstract PupilService bindPupilService();



}
