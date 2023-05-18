package com.example.AndroidLab4

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.AndroidLab4.databinding.TaskItemCellBinding
//import com.example.todolisttutorial.databinding.TaskItemCellBinding

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTaskItem(taskItem: TaskItem) {
        binding.name.text =
            if (taskItem.name.length > 15) taskItem.name.substring(0, 14) + "..." else taskItem.name
        if (taskItem.completed == true) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.desc.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        binding.taskCellContainer.setOnClickListener {
            clickListener.showTaskItem(taskItem)
        }

        binding.desc.text =
            if (taskItem.desc.length > 70) taskItem.desc.substring(0, 70) + "..." else taskItem.desc
    }
}