package com.hyprful.firstmvvm.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hyprful.firstmvvm.db.entity.PupilEntity;

import java.util.List;

@Dao
public interface PupilDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PupilEntity pupilEntity);

    @Query("SELECT * from pupils ORDER BY id DESC")
    LiveData<List<PupilEntity>> getAllPupils();


    @Query("SELECT * FROM pupils WHERE id = :pupilEntityId")
    LiveData<PupilEntity> loadById(int pupilEntityId);


    @Query("SELECT * FROM pupils WHERE name = :name and country = :country")
    LiveData<PupilEntity> loadByNameAndCountry(String name, String country);


}
