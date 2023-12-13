package com.example.to_do_list

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.to_do_list.databinding.ActivityMainBinding
import com.example.to_do_list.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewTaskSheet(var taskItem: TaskItem?): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null){  //Checa se a uma tarefa ao abrir o bottom sheet e se hover muda o titulo
            binding.taskTitle.text = "Editar a Tarefa"
            val editable = Editable.Factory.getInstance()
            if(taskItem!!.dueTime != null){ //Se a tarefa conter um horario a ser comprido e mostra o horario no lugar do texto do botão
                dueTime = taskItem!!.dueTime!!
                updateTimeButtonText() //Função que muda o botão
            }
        } else {
            binding.taskTitle.text = "Nova Tarefa"
        }
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener{
            saveAction()
        }
        binding.timePickerButton.setOnClickListener{
            openTimePiker()
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun openTimePiker() { //Função para abrio o seletor de horario
        if (dueTime==null) //se nao ouver horario selecionado
            dueTime = LocalTime.now()// define com o horario atual
        val listener = TimePickerDialog.OnTimeSetListener {_,selectedHour,selectedMinute ->
            dueTime = LocalTime.of(selectedHour,selectedMinute) // atribui o valor selecionado a variavel
            updateTimeButtonText() //Muda o botão para mostrar o horario selecionado
        }
        val dialog = TimePickerDialog(activity,listener,dueTime!!.hour,dueTime!!.minute,true) // Cria o dialogo para selecionar o horário
        dialog.setTitle("Selecionar Horário") //Define o titulo do dialogo
        dialog.show()
    }

    private fun saveAction() {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        if(taskItem == null){ // Checa se a tarefa e nova
            val newTask = TaskItem(name,desc,dueTime,null) // Atribui os valores da nova Classe TaskItem a variavel newTask
            taskViewModel.addTaskItem(newTask) //Adiciona a nova tarefa (newTask) com a função addTaskItem contida em TaskViewModel
        } else{
            taskViewModel.updateTaskItem(taskItem!!.id,name,desc,dueTime, completedDate = null)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss() //Fecha o fragmento
    }

    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute) //Muda o testo do botão de selecionar horario
    }
}