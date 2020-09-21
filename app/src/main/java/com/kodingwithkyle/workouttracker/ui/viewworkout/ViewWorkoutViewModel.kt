package com.kodingwithkyle.workouttracker.ui.viewworkout

import androidx.lifecycle.ViewModel
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo

class ViewWorkoutViewModel(workoutRepo: WorkoutRepo, private val workoutId: Int) : ViewModel() {

    val exercises = workoutRepo.fetchWorkout(workoutId)
}