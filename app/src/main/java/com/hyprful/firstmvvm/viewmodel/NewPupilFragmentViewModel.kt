package com.hyprful.firstmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyprful.firstmvvm.DataRepository
import com.hyprful.firstmvvm.db.entity.PupilEntity
import com.hyprful.firstmvvm.api.model.Pupil
import com.hyprful.firstmvvm.api.repository.PupilServerRepository
import com.hyprful.firstmvvm.api.repository.PupilServerService
import com.hyprful.firstmvvm.view.viewbinding.PupilDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewPupilFragmentViewModel : ViewModel() {
    val TAG = javaClass.name

    lateinit var pupilServerService: PupilServerService

//    lateinit var pupil: Pupil

    lateinit var data : PupilDataBinding
    lateinit var dataRepository: DataRepository;
    lateinit var pupilServerRepository: PupilServerRepository


    fun insertLocalData(pupil: Pupil): LiveData<Boolean> {
        val pupilEntity = PupilEntity(pupil)
        return dataRepository.insert(pupilEntity)
    }

    fun createServerRecord(pupil: Pupil): MutableLiveData<Boolean> {
        return pupilServerRepository.create(pupil)
    }

    fun getDbPupil(): LiveData<MutableList<PupilEntity>> {
        return dataRepository.allPupil
    }


//    fun setBindingData(){
//        data.pupil = pupil
//    }

    fun loadById(id: Int): LiveData<PupilEntity> {
        return dataRepository.loadById(id)
    }

    fun createPupil(): MediatorLiveData<Boolean> {
        val response = MutableLiveData<Pupil>()
        var result =  MediatorLiveData<Boolean>();

        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)



        result.addSource(createServerRecord(data.pupil)){
            if (!it){
                Log.i(TAG, "Server Response $it")
                result.value = it

                uiScope.launch(Dispatchers.IO){
                    insertLocalData(data.pupil)

//                    result.addSource( ){
//                        result.value = it
//                    }
//                    result.addSource( ){
//                        Log.i(TAG, "Database Response $it")
//                        result.value = it
//                    }
                }
            }
        }




        return result;

    }

}