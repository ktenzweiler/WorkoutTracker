package com.kodingwithkyle.workouttracker.data.model.workout

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutRepo internal constructor(private val workoutDao: WorkoutDao) {

    fun fetchWorkouts() = workoutDao.fetchWorkouts()

    fun fetchWorkout(workoutId: Int) = workoutDao.fetchWorkoutById(workoutId)

    suspend fun insertWorkout(workout: Workout) {
        withContext(Dispatchers.IO) {
            workoutDao.insertWorkout(workout)
        }
    }

    companion object {

        @Volatile private var instance: WorkoutRepo? = null

        fun getInstance(workoutDao: WorkoutDao) =
            instance ?: synchronized(this) {
                instance
                    ?: WorkoutRepo(workoutDao).also { instance = it }
            }
    }
}