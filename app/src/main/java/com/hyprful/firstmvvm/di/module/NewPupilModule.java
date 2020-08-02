package com.hyprful.firstmvvm.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.hyprful.firstmvvm.DataRepository;
import com.hyprful.firstmvvm.api.repository.PupilServerRepository;
import com.hyprful.firstmvvm.api.repository.PupilServerService;
import com.hyprful.firstmvvm.view.fragment.NewPupilFragment;
import com.hyprful.firstmvvm.view.viewbinding.PupilDataBinding;
import com.hyprful.firstmvvm.viewmodel.NewPupilFragmentViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NewPupilModule {


    @Provides
    NewPupilFragmentViewModel provideNewPupilFragmentViewModel(NewPupilFragment fragment, PupilServerService pupilServerService, DataRepository dataRepository, PupilServerRepository pupilServerRepository){
        NewPupilFragmentViewModel mainFragmentViewModel = new ViewModelProvider(fragment).get(NewPupilFragmentViewModel.class);
        mainFragmentViewModel.setPupilServerService(pupilServerService);
        mainFragmentViewModel.setData(new PupilDataBinding());
        mainFragmentViewModel.setDataRepository(dataRepository);
        mainFragmentViewModel.setPupilServerRepository(pupilServerRepository);
        return mainFragmentViewModel;
    }


}
