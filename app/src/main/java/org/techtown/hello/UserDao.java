package org.techtown.hello;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAlluser();

    @Insert
    void insertUser(User user);

    @Delete
    void userDelete(User user);

    @Update
    void userUpdate(User user);



}
