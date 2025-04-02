package com.example.bazakresow;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KresyA {

    @Insert
    void insert(Kres kres);
    @Delete
    void delete(Kres kres);
    @Update
    void update(Kres kres);
    @Query("Select * FROM kresy")
    List<Kres> selectAll();
    @Query("Select nazwa FROM kresy WHERE `rok` >:rok")
    List<String> namesAfter2024(int rok);

}