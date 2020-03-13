package com.freeoda.franktirkey.smartmanagementforengineers.localDBForBKs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao{

    @Query("Select * FROM userDetails")
    List<User> getAll();

    @Insert(onConflict = 1)
    void insertAll(User... user);

    @Query("DELETE FROM USERDETAILS")
    void deleteAll();

}
