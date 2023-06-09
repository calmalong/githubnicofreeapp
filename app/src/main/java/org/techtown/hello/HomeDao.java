package org.techtown.hello;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface HomeDao {
    @Insert
    void insert(HomeEntity entity);
    @Query("SELECT id, startdate, stage, msgbox FROM HomeEntity LIMIT 1")
    HomeEntity getCurrentStage();
}