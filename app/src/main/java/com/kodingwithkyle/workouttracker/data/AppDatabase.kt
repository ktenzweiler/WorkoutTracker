package com.kodingwithkyle.workouttracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutDao
import com.kodingwithkyle.workouttracker.data.model.workout.Workout

@Database(entities = [Workout::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "workout-db")
                .build()
        }
    }
}