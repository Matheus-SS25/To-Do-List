package com.example.to_do_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel : ViewModel() {

    var taskItems = MutableLiveData<MutableList<TaskItem>>()
    init {
        taskItems.value = mutableListOf() // Inicializa a lista antes de aplicar a classe
    }
    //Função para criar nova tarefa
    fun addTaskItem(newTask: TaskItem){
        val list = taskItems.value //Variavel com o valor da lista taskItem
        list!!.add(newTask) // Adiciona a tarefa a list
        taskItems.postValue(list) //Posta a nova tarefa a lista
    }
    //Função para o update da tarefa
    fun updateTaskItem(id : UUID, name : String, desc: String, dueTime: LocalTime?,completedDate: LocalDate?){
        val list = taskItems.value //Variavel com o valor da lista taskItem
        val task = list!!.find{it.id == id}!! //Procura nas listas o id e attribute a lista correspondente a variavel task
        task.name = name //Atribui os valores passados a os capos da lista
        task.desc = desc
        task.dueTime = dueTime
        task.completedDate = null
        taskItems.postValue(list)
    }
    //Função para setar a tarefa como completa
    fun setCompleted(taskItem: TaskItem){
        val list = taskItems.value //Variavel com o valor da lista taskItem
        val task = list!!.find{it.id == taskItem.id}!!//Procura nas lista o id e attribute a lista correspondente a variavel task
        if (task.completedDate == null) //Checa se não a data de conclusão
            task.completedDate = LocalDate.now() //Atribui a data de conclusão atual
        taskItems.postValue(list) //Posta a modificação da tarefa a lista
    }
    //Função para setar a tarefa como incompleta
    fun setUncompleted(taskItem: TaskItem){
        val list = taskItems.value //Variavel com o valor da lista taskItem
        val task = list!!.find{it.id == taskItem.id}!!//Procura nas lista o id e attribute a lista correspondente a variavel task
        if (task.completedDate != null) //Checa se a  uma data de conclusão
            task.completedDate = null//Atribui null a data de conclusão
        taskItems.postValue(list) //Posta a modificação da tarefa a lista
    }
}
