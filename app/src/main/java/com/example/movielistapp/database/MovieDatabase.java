package com.example.movielistapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NowPlaying.class, Popular.class, TopRated.class, Upcoming.class}, version = 4, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract NowPlayingDao nowPlayingDao();
    public abstract PopularDao popularDao();
    public abstract TopRatedDao topRatedDao();
    public abstract UpcomingDao upcomingDao();
    private static volatile MovieDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService dataBaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MovieDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (MovieDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class,"movie_dp")
                            .fallbackToDestructiveMigration()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /*
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    dataBaseWriteExecutor.execute(() ->{

                    });
                }
            };

     */
}
