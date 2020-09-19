package com.kodingwithkyle.workouttracker.ui.add

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kodingwithkyle.workouttracker.data.model.exercise.Exercise
import com.kodingwithkyle.workouttracker.data.model.workout.Workout
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddWorkoutViewModel internal constructor(private val workoutRepo: WorkoutRepo) : ViewModel() {

    fun saveWorkout(exercises: ArrayList<Exercise>) {
        val df = DateFormat()
        val date = DateFormat.format("MM-dd-yyyy", Date())
        val gson = Gson()
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepo.insertWorkout(Workout(date.toString(), "Arms", gson.toJson(exercises)))
        }
    }
}