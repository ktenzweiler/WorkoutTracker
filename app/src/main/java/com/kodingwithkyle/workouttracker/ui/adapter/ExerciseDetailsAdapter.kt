package com.kodingwithkyle.workouttracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodingwithkyle.workouttracker.R
import com.kodingwithkyle.workouttracker.data.model.exercise.Exercise

class ExerciseDetailsAdapter: RecyclerView.Adapter<ExerciseDetailsAdapter.ViewHolder>() {

    private val mExercises = ArrayList<Exercise>()

    fun setExercise(exercises: ArrayList<Exercise>) {
        mExercises.addAll(exercises)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.list_item_exercise_detail, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mExercises[position])
    }

    override fun getItemCount() = mExercises.size

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(exercise: Exercise) {
            view.findViewById<TextView>(R.id.exercise_name).text = exercise.name
            view.findViewById<TextView>(R.id.sets).text = exercise.sets.toString()
            view.findViewById<TextView>(R.id.reps).text = exercise.reps.toString()
            view.findViewById<TextView>(R.id.weight).text = exercise.weight.toString()
        }
    }
}