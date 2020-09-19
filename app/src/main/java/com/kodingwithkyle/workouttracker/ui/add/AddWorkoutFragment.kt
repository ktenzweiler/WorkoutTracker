package com.kodingwithkyle.workouttracker.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kodingwithkyle.workouttracker.R
import com.kodingwithkyle.workouttracker.data.AppDatabase
import com.kodingwithkyle.workouttracker.data.model.exercise.Exercise
import com.kodingwithkyle.workouttracker.data.model.workout.Workout
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo
import com.kodingwithkyle.workouttracker.ui.adapter.ExerciseAdapter

class AddWorkoutFragment : Fragment() {

    companion object {
        const val TAG = "ADD_WORKOUT_FRAGMENT"
        fun newInstance() = AddWorkoutFragment()
    }

    private val mAdapter = ExerciseAdapter()

    private val addWorkoutViewModel : AddWorkoutViewModel by viewModels {
        AddWorkoutVMFactory(WorkoutRepo(AppDatabase.getInstance(requireContext()).workoutDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_workout_fragment, container, false)
        view.findViewById<RecyclerView>(R.id.exercise_list).adapter = mAdapter
        val list = ArrayList<Exercise>()
        list.add(Exercise("",0,0,0.0))
        mAdapter.setExercises(list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.save_fab).setOnClickListener {
            addWorkoutViewModel.saveWorkout(mAdapter.getExercises())
        }
    }
}