package com.kodingwithkyle.workouttracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodingwithkyle.workouttracker.MainActivity
import com.kodingwithkyle.workouttracker.R
import com.kodingwithkyle.workouttracker.data.model.workout.Workout
import com.kodingwithkyle.workouttracker.ui.main.MainFragment

class WorkoutAdapter : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    private val workouts = ArrayList<Workout>()

    fun setWorkouts(workouts: List<Workout>) {
        this.workouts.clear()
        this.workouts.addAll(workouts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.list_item_workout, parent, false)
        return WorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(workouts[position])
    }

    override fun getItemCount() = workouts.size

    fun getWorkout(position: Int) = workouts[position]

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun bind(workout: Workout) {
            itemView.setOnClickListener(this)
            itemView.findViewById<TextView>(R.id.title).text = workout.date
            itemView.findViewById<TextView>(R.id.date).text = workout.muscle
        }

        override fun onClick(v: View?) {
            v?.let {
                (it.context as MainActivity).onListItemClick(adapterPosition)
            }
        }
    }
}