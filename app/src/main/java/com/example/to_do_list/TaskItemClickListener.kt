package com.example.to_do_list

interface TaskItemClickListener {
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
    fun uncompleteTaskItem(taskItem: TaskItem)
    fun deleteTaskItem (taskItem: TaskItem)
}