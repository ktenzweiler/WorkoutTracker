package com.kodingwithkyle.workouttracker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo

class MainVMFactory(private val workoutRepo: WorkoutRepo)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MainViewModel(workoutRepo) as T
}