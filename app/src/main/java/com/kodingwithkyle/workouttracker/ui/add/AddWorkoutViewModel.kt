package com.kodingwithkyle.workouttracker.ui.add

import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kodingwithkyle.workouttracker.data.model.exercise.Exercise
import com.kodingwithkyle.workouttracker.data.model.workout.Workout
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class AddWorkoutViewModel internal constructor(
    private val workoutRepo: WorkoutRepo
) : ViewModel() {

    fun saveWorkout(muscleGroup: String, exercises: ArrayList<Exercise>) {
        val cal = Calendar.getInstance()
        val date = cal.timeInMillis
        val tempList = ArrayList<Exercise>()
        for (e in exercises) {
            if (e.name.isNotEmpty()) {
                tempList.add(e)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val exercisesString = Gson().toJson(tempList)
            Log.d("Exercises", exercisesString)
            workoutRepo.insertWorkout(
                Workout(
                    id = 0,
                    date,
                    muscleGroup,
                    exercisesString
                )
            )
        }
    }
}