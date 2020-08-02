package com.hyprful.firstmvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hyprful.firstmvvm.db.AppDatabase;
import com.hyprful.firstmvvm.db.dao.PupilDao;
import com.hyprful.firstmvvm.db.entity.PupilEntity;

import java.util.List;

public class DataRepository {

    private PupilDao mPupilDao;
    private LiveData<List<PupilEntity>> mAllPupils;
    private AppDatabase db;

    public DataRepository(AppDatabase appDatabase) {
        db = appDatabase;
        mPupilDao = appDatabase.pupilDao();
        mAllPupils = mPupilDao.getAllPupils();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<PupilEntity>> getAllPupil() {
        return mAllPupils;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public LiveData<Boolean> insert(PupilEntity pupilEntity) {
        MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
        if (mPupilDao == null)
            mPupilDao = db.pupilDao();

        //        booleanMutableLiveData.setValue(false);

        db.runInTransaction(() -> {
            mPupilDao.insert(pupilEntity);
        });

        return booleanMutableLiveData;
    }

    public LiveData<PupilEntity> loadById(int id){
        return mPupilDao.loadById(id);
    }


}
