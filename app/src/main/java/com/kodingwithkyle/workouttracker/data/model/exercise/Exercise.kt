package com.kodingwithkyle.workouttracker.data.model.exercise

data class Exercise(
    var name: String,
    var reps: Int,
    var sets: Int,
    var weight: Double
)