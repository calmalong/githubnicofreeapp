package org.techtown.hello;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Record.class, HomeEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract RecordDao recordDao();

    public abstract HomeDao homeDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context){

        //Instance 가 null 이면 초기화
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
            , AppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
