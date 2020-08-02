package com.hyprful.firstmvvm.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hyprful.firstmvvm.db.dao.PupilDao;
import com.hyprful.firstmvvm.db.entity.PupilEntity;

import static com.hyprful.firstmvvm.Constant.DB_NAME;

@Database(entities = {PupilEntity.class}, version = 2, exportSchema = false)
public  abstract class AppDatabase extends RoomDatabase {


    public abstract PupilDao pupilDao();
    private static volatile AppDatabase _instance;

    public static AppDatabase getDatabase(final Context context) {
        if (_instance == null) {
            synchronized (AppDatabase.class) {
                if (_instance == null) {
                    _instance =  Room.databaseBuilder(context,
                            AppDatabase.class, DB_NAME)
                            .addMigrations(DataMigration.MIGRATION_1_2)
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return _instance;
    }


}

