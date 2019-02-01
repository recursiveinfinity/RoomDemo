package com.example.sidd.roomdemo.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Person {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "personName")
    public String name;

    @ColumnInfo(name = "personAge")
    public int age;

    @ColumnInfo(name = "personEmail")
    public String email;
}
