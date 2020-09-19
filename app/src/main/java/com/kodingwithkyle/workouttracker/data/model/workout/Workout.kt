package com.kodingwithkyle.workouttracker.data.model.workout

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey @ColumnInfo(name = "date") val date: String,
    val muscle: String,
    val exercises: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Workout

        if (date != other.date) return false
        if (muscle != other.muscle) return false
        if (exercises != other.exercises) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + muscle.hashCode()
        result = 31 * result + exercises.hashCode()
        return result
    }
}