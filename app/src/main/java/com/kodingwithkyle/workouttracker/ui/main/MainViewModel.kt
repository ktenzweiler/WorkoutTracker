package com.kodingwithkyle.workouttracker.ui.main

import android.view.View
import androidx.lifecycle.ViewModel
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo

class MainViewModel internal constructor(workoutRepo: WorkoutRepo): ViewModel() {

    val workouts = workoutRepo.fetchWorkouts()

    fun handleFabClick() {

    }

    fun handleWorkoutClick(workoutId: String) {

    }
}