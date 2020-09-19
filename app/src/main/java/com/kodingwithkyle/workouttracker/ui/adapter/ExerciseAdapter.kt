package com.kodingwithkyle.workouttracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodingwithkyle.workouttracker.R
import com.kodingwithkyle.workouttracker.data.model.exercise.Exercise

class ExerciseAdapter() : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    private val mExercises = ArrayList<Exercise>()

    private val mClickListener = View.OnClickListener() {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_exercise, parent, false)
        val holder = ViewHolder(view)
        view.findViewById<ImageButton>(R.id.delete).setOnClickListener {
            mExercises.removeAt(holder.adapterPosition)
            notifyItemChanged(holder.adapterPosition)
        }
        view.findViewById<ImageButton>(R.id.save_btn).setOnClickListener {
            mExercises[holder.adapterPosition].name =
                view.findViewById<EditText>(R.id.exercise_name).text.toString()
            view.clearFocus()
            notifyItemChanged(holder.adapterPosition)
        }
        view.findViewById<ImageButton>(R.id.subtract_sets).setOnClickListener {
            if (mExercises[holder.adapterPosition].sets > 0) {
                mExercises[holder.adapterPosition].sets--
                notifyItemChanged(holder.adapterPosition)
            }
        }
        view.findViewById<ImageButton>(R.id.add_sets).setOnClickListener {
            mExercises[holder.adapterPosition].sets++
            notifyItemChanged(holder.adapterPosition)
        }
        view.findViewById<ImageButton>(R.id.subtract_reps).setOnClickListener {
            if (mExercises[holder.adapterPosition].reps > 0) {
                mExercises[holder.adapterPosition].reps--
                notifyItemChanged(holder.adapterPosition)
            }
        }
        view.findViewById<ImageButton>(R.id.add_reps).setOnClickListener {
            mExercises[holder.adapterPosition].reps++
            notifyItemChanged(holder.adapterPosition)
        }
        view.findViewById<ImageButton>(R.id.minus_weight).setOnClickListener {
            if (mExercises[holder.adapterPosition].weight > 0) {
                mExercises[holder.adapterPosition].weight -= 2.5
                notifyItemChanged(holder.adapterPosition)
            }
        }
        view.findViewById<ImageButton>(R.id.add_weight).setOnClickListener {
            mExercises[holder.adapterPosition].weight += 2.5
            notifyItemChanged(holder.adapterPosition)
        }
        return holder
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.setOnClickListener(null)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mExercises[position])
    }

    override fun getItemCount() = mExercises.size

    fun setExercises(exercises: ArrayList<Exercise>) {
        this.mExercises.addAll(exercises)
        notifyDataSetChanged()
    }

    fun getExercises() = mExercises

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(exercise: Exercise) {
            itemView.findViewById<TextView>(R.id.exercise_name).text = exercise.name
            itemView.findViewById<TextView>(R.id.sets_count).text = exercise.sets.toString()
            itemView.findViewById<TextView>(R.id.reps_count).text = exercise.reps.toString()
            itemView.findViewById<TextView>(R.id.weight).text = exercise.weight.toString()
        }
    }
}