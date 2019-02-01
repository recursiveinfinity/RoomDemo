package com.example.sidd.roomdemo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
