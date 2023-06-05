package org.techtown.hello;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "HomeEntity")
public class HomeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "startdate")
    public String startdate;

    @ColumnInfo(name = "stage")
    public String stage;

    @ColumnInfo(name = "msgbox")
    public String msgbox;
}
