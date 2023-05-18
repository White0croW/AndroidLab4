package com.example.AndroidLab4

interface TaskItemClickListener
{
    fun showTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
}