package com.kodingwithkyle.workouttracker.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo

class AddWorkoutVMFactory(private val workoutRepo: WorkoutRepo) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        AddWorkoutViewModel(workoutRepo) as T
}