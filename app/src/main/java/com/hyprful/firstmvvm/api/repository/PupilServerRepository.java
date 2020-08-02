package com.hyprful.firstmvvm.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hyprful.firstmvvm.api.model.ApiResponse;
import com.hyprful.firstmvvm.api.model.Pupil;
import com.hyprful.firstmvvm.api.model.PupilResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class PupilServerRepository {
    private PupilServerService pupilServerService;

    @Inject
    public PupilServerRepository(PupilServerService pupilServerService) {
        this.pupilServerService = pupilServerService;
    }

    public LiveData<Pupil> getPupilDetails(int userID) {
        final MutableLiveData<Pupil> data = new MutableLiveData<>();
        return data;
    }



    public Observable<PupilResponse> loadPupils() {
        final MutableLiveData<ApiResponse<PupilResponse>> data = new MutableLiveData<>();

        return  pupilServerService.getPupilList(1);

//        pupilServerService.getPupilList(1).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((pupilResponse) ->{
//                    SuccessApiResponse<PupilResponse> successApiResponse =   new SuccessApiResponse<PupilResponse>();
//                    successApiResponse.setResult(pupilResponse);
//                    data.setValue(successApiResponse);
//                } , (error) -> {
//                    ErrorApiResponse<PupilResponse> errorApiResponse =   new ErrorApiResponse<PupilResponse>();
//                    data.setValue(errorApiResponse);
//
//                });
//
//        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MutableLiveData<Boolean> create(Pupil pupil){
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        pupilServerService.create(pupil).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe((result) -> {
                data.setValue(true);
            }, (error) -> {
                data.setValue(false);

            });
        return data;
    }
}
