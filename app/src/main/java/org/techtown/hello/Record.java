package org.techtown.hello;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Record {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "userSituation")
    public  String userSituation;

    @ColumnInfo (name = "userFeeling")
    public String userFeeling;

}