package com.hyprful.firstmvvm.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.hyprful.firstmvvm.DataRepository;
import com.hyprful.firstmvvm.api.repository.PupilServerRepository;
import com.hyprful.firstmvvm.api.repository.PupilServerService;
import com.hyprful.firstmvvm.view.adapter.PupilAdapter;
import com.hyprful.firstmvvm.view.fragment.MainFragment;
import com.hyprful.firstmvvm.viewmodel.MainFragmentViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public  class MainFragmentModule {

//    @ContributesAndroidInjector
//    abstract MainFragment contributeMainFragment();
//

    @Provides
    PupilAdapter providePupilAdapeter(MainFragment mainFragment){
        return  new PupilAdapter(mainFragment);
    }

    @Provides
    MainFragmentViewModel provideMainFragmentViewModel(MainFragment mainFragment,
                                                       PupilServerService pupilServerService,
                                                       PupilServerRepository pupilServerRepository,
                                                       DataRepository dataRepository
                                                       ){
        MainFragmentViewModel mainFragmentViewModel = new ViewModelProvider(mainFragment).get(MainFragmentViewModel.class);
//        mainFragmentViewModel.setPupilServerService(pupilServerService);
        mainFragmentViewModel.setPupilServerRepository(pupilServerRepository);
        mainFragmentViewModel.setDataRepository(dataRepository);
        return mainFragmentViewModel;
    }

//    @Provides
//    static PupilServerService provideRetrofitService(Retrofit retrofit) {
//        return retrofit.create(PupilServerService.class);
//    }
}
