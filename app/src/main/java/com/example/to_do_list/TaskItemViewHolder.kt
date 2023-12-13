package com.example.to_do_list

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder (
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
):RecyclerView.ViewHolder(binding.root) {
    private val timeFormat=DateTimeFormatter.ofPattern("HH:mm") // Define o padrão utilizado para o horário

    fun bindTaskItem(taskItem: TaskItem){
        binding.name.text = taskItem.name

        if(taskItem.isCompleted()){ //Muda o estilo do texto de acordo com a função isCompleted, colocando um risco no texto e horário para indicar tarefa completa
        binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        binding.completeButton.setImageResource(taskItem.imageResource()) //Muda a imagem do botão de conclusão de tarefa de acordo com a função imageResource
        binding.completeButton.setColorFilter(taskItem.imageColor(context)) //Muda a cor da imagem do botão de conclusão de tarefa de acordo com a função imageColor

        binding.completeButton.setOnClickListener{
           if(taskItem.completedDate == null) clickListener.completeTaskItem(taskItem) else clickListener.uncompleteTaskItem(taskItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }
        binding.deleteButton.setOnClickListener{
            clickListener.deleteTaskItem(taskItem)
        }
        if (taskItem.dueTime != null) //Checa se a um horário para conclusão e coloca dentro do formato
            binding.dueTime.text = timeFormat.format(taskItem.dueTime) else
                binding.dueTime.text = "" // Se não a nenhum horário de conclusão deixa o campo em branco
    }
}