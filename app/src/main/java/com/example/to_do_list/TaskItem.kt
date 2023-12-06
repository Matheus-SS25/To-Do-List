package com.example.to_do_list

import android.content.Context
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID


//Classe que define os atributos de uma tarefa e algumas funções relacionadas
class TaskItem (
    var name: String, //Nome da tarefa
    var desc: String, //Descrição da tarefa
    var dueTime: LocalTime?, //Tempo para comprimento da tarefa, ? - permite null
    var completedDate: LocalDate?, // Data em que a tarefa foi completada, ? - permite null
    var id: UUID = UUID.randomUUID() //Cria o id randomicamente
)
{
    fun isCompleted() = completedDate != null //Funçao define que uma tarefa completada tem que ser diferente de null
    fun imageResource() : Int = if (isCompleted()) R.drawable.check_box_24 else R.drawable.check_box_blank_24 //Muda a imagem do botão se a tarefas for definida como completa
    fun imageColor(context:Context): Int = if (isCompleted()) azul(context) else preto(context) //Muda a cor da imagem do botão de acordo com seu estado

    private fun azul(context:Context) = ContextCompat.getColor(context, R.color.dark_blue)
    private fun preto (context:Context) = ContextCompat.getColor(context, R.color.black)
}