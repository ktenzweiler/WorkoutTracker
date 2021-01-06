package com.kodingwithkyle.workouttracker.ui.viewworkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.observe
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kodingwithkyle.workouttracker.R
import com.kodingwithkyle.workouttracker.data.AppDatabase
import com.kodingwithkyle.workouttracker.data.model.exercise.Exercise
import com.kodingwithkyle.workouttracker.data.model.workout.WorkoutRepo
import com.kodingwithkyle.workouttracker.ui.adapter.ExerciseDetailsAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewWorkoutFragment : Fragment() {

    companion object {
        const val TAG = "VIEW_WORKOUT_FRAGMENT"
        fun newInstance(workoutId: Int): ViewWorkoutFragment {
            val frag = ViewWorkoutFragment()
            val args = Bundle()
            args.putInt("ID", workoutId)
            frag.arguments = args
            return frag
        }
    }

    private val mAdapter = ExerciseDetailsAdapter()

    private val viewModel: ViewWorkoutViewModel by viewModels {
        arguments?.let {
            ViewWorkoutVMFactory(
                WorkoutRepo(AppDatabase.getInstance(requireContext()).workoutDao()),
                it.getInt("ID")
            )
        }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_workout_fragment, container, false)
        view.findViewById<RecyclerView>(R.id.exercise_list).adapter = mAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateFormat = SimpleDateFormat("MMMM dd yyyy", Locale.getDefault())
        val date = Date()
        viewModel.exercises.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.muscle_group).text = it.muscle
            view.findViewById<TextView>(R.id.date).text = dateFormat.format(date)
            val exerciseType = object : TypeToken<ArrayList<Exercise>>() {}.type
            val exercises = Gson().fromJson<ArrayList<Exercise>>(it.exercises, exerciseType)
            mAdapter.setExercise(exercises)
        }
    }
}