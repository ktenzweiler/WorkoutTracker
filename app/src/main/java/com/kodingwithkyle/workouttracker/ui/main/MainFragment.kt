package com.kodingwithkyle.workouttracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kodingwithkyle.workouttracker.R
import com.kodingwithkyle.workouttracker.data.AppDatabase
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kodingwithkyle.workouttracker.data.model.workout.Workout
import com.kodingwithkyle.workouttracker.ui.adapter.WorkoutAdapter
import com.kodingwithkyle.workouttracker.ui.add.AddWorkoutFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val homeViewModel: MainViewModel by viewModels {
        MainVMFactory(WorkoutRepo(AppDatabase.getInstance(requireContext()).workoutDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workoutAdapter = WorkoutAdapter()
        view.findViewById<RecyclerView>(R.id.workouts).adapter = workoutAdapter
        subscribeUI(workoutAdapter)
        view.findViewById<FloatingActionButton>(R.id.add_workout_btn).setOnClickListener{
            onAddWorkoutClick(it)
        }
    }

    private fun subscribeUI(adapter: WorkoutAdapter) {
        homeViewModel.workouts.observe(viewLifecycleOwner) {
            adapter.setWorkouts(it)
        }
    }

    fun onAddWorkoutClick(v: View) {
        requireActivity().supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, AddWorkoutFragment.newInstance(), AddWorkoutFragment.TAG)
                .addToBackStack(AddWorkoutFragment.TAG)
                .commit()
        }
    }
}