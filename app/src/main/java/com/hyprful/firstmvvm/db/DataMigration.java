package com.hyprful.firstmvvm.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DataMigration {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE pupils ADD COLUMN synced  BOOLEAN");
            database.execSQL("UPDATE pupils SET synced = 'false'");

        }
    };
}
