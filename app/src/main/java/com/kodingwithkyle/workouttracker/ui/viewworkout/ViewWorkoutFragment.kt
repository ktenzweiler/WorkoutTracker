package com.kodingwithkyle.workouttracker.ui.viewworkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kodingwithkyle.workouttracker.R
import com.kodingwithkyle.workouttracker.data.AppDatabase
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo

class ViewWorkoutFragment : Fragment() {

    companion object {
        const val TAG = "VIEW_WORKOUT_FRAGMENT"
        fun newInstance(workoutId: Int) : ViewWorkoutFragment {
            val frag = ViewWorkoutFragment()
            val args = Bundle()
            args.putInt("ID", workoutId)
            frag.arguments = args
            return frag
        }
    }

    private val viewModel: ViewWorkoutViewModel by viewModels {
        ViewWorkoutVMFactory(WorkoutRepo(AppDatabase.getInstance(requireContext()).workoutDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_workout_fragment, container, false)
    }
}