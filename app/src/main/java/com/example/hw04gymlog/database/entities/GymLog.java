package com.example.hw04gymlog.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.hw04gymlog.database.GymLogDatabase;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity class for the database, utilizes Room annotations.
 * HW04: GymLog
 * Followed along with the videos
 * Brandon Nhep
 */
@Entity(tableName = GymLogDatabase.GYM_LOG_TABLE)
public class GymLog {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private String exercise;
    private double weight;
    private int reps;
    private LocalDateTime date;

    private int userID;

    public GymLog(String exercise, double weight, int reps, int userID) {
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.userID = userID;
        date = LocalDateTime.now();
    }

    @NonNull
    @Override
    public String toString() {
        return  exercise + '\n' +
                "weight:" + weight + '\n' +
                "reps:" + reps + '\n' +
                "date:" + date.toString() + '\n' +
                "=-=-=-=-=-=-=\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GymLog gymLog = (GymLog) o;
        return id == gymLog.id && Double.compare(weight, gymLog.weight) == 0 && reps == gymLog.reps && userID == gymLog.userID && Objects.equals(exercise, gymLog.exercise) && Objects.equals(date, gymLog.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exercise, weight, reps, date, userID);
    }
}
