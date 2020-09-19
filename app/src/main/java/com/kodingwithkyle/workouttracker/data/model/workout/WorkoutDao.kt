package com.kodingwithkyle.workouttracker.data.model.workout

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutDao {
@Query("SELECT * FROM workouts ORDER BY date")
    fun fetchWorkouts(): LiveData<List<Workout>>

    @Query("SELECT * FROM workouts WHERE date = :workoutId")
    fun fetchWorkoutById(workoutId: String) : LiveData<Workout>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workout: Workout)
}