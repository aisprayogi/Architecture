package edu.cs4730.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * This is the Dao for the score "db"  It provides the accessor methods to the database.
 *
 */
@Dao
public interface ScoreDao {
    @Query("SELECT * FROM score")
    List<Score> loadAllScores();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Score> scores);

    @Query("select * from score where score = :scoreValue")
    List<Score> loadScore(int scoreValue);

}
