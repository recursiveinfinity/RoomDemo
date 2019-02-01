package com.example.sidd.roomdemo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM Person")
    Flowable<List<Person>> getAllPeople();

//    @Query("SELECT * FROM Person WHERE personEmail (:email)")
//    void getPerson(String email);

    @Insert
    void insert(Person... person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);


}
