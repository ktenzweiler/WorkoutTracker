package com.kodingwithkyle.workouttracker.ui.viewworkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo

class ViewWorkoutVMFactory(private val workoutRepo: WorkoutRepo): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ViewWorkoutViewModel(workoutRepo) as T
}