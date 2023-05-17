package org.techtown.hello;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDao {

    @Query("SELECT * FROM record")
    List<Record> getAllRecord();

    @Insert
    void insertRecord(Record record);

    @Delete
    void recordDelete(Record record);

    @Update
    void recordUpdate(Record record);



}
