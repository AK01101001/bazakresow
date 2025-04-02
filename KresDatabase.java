package com.example.bazakresow;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Kres.class},version = 1)
public abstract class KresDatabase extends RoomDatabase {
    public KresDatabase instance = this;
    public abstract KresyA zwrocDao();
}
