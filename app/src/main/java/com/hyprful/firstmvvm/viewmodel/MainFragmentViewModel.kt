package com.hyprful.firstmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.hyprful.firstmvvm.DataRepository
import com.hyprful.firstmvvm.api.model.ApiResponse
import com.hyprful.firstmvvm.api.model.ErrorApiResponse
import com.hyprful.firstmvvm.api.model.SuccessApiResponse
import com.hyprful.firstmvvm.api.model.Pupil
import com.hyprful.firstmvvm.api.model.PupilResponse
import com.hyprful.firstmvvm.api.repository.PupilServerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFragmentViewModel : ViewModel() {

  //    lateinit var pupilServerService: PupilServerService
  lateinit var pupilServerRepository: PupilServerRepository
  lateinit var dataRepository: DataRepository;


  fun fetchData(): LiveData<ApiResponse<PupilResponse>> {
    var data = MutableLiveData<ApiResponse<PupilResponse>>()

    val result = MediatorLiveData<ApiResponse<PupilResponse>>()

    pupilServerRepository.loadPupils().subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        { pupilResponse ->
          var successApiResponse : SuccessApiResponse<PupilResponse> =
            SuccessApiResponse<PupilResponse>();
          successApiResponse.result = pupilResponse
          data.value = (successApiResponse);
          result.addSource(data) { result.value = it }
        },
        {   error ->

          var errorApiResponse : ErrorApiResponse<PupilResponse> =
            ErrorApiResponse<PupilResponse>();
          errorApiResponse.error = error
          data.value = (errorApiResponse);

          result.addSource(data){ result.value = it }

          var localData = fetchDbDataForServerError()
          result.addSource(localData) { result.value = it }
        }
      )

    return result;
  }



  fun fetchDbDataForServerError(): LiveData<ApiResponse<PupilResponse>> {
    val selectedDevice = Transformations.map(dataRepository.allPupil) { pupilData  ->
      Log.i("A0ew9jw0efj0wef9we", pupilData.toString())
      dataRepository.allPupil.observeForever {  }

      var resposneData = pupilData
      var converedPupilData : ArrayList<Pupil> = ArrayList();

      if(resposneData != null) {
        resposneData!!.forEach {
          converedPupilData.add(it.generatePupil())
        }
      }
      var reponse = PupilResponse(0, converedPupilData, 0, 0);
      var successApiResponse : ApiResponse<PupilResponse> =
        SuccessApiResponse<PupilResponse>();
      successApiResponse.result = reponse

      return@map successApiResponse
    }

    return selectedDevice;
  }



}