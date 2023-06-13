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
    void deleteRecord(Record record);

    @Update
    void updateRecord(Record record);
    @Query("SELECT * FROM record WHERE uid = :uid")
    Record getRecordById(int uid);

    @Query("SELECT * FROM record LIMIT :count")
    List<Record> getRecentRecords(int count);
}
