package edu.cs4730.roomdemo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Score  Plain Old Java Object  This needs to provide getters and setters for everything plus the
 * constructors for the object.   The table in the database is not specified, so it will be score (the Class name)
 */

@Entity
public class Score {
    public @PrimaryKey
    int id;

    public String name;
    public int score;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score() {
    }

    public Score(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }



}
