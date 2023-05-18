package com.example.AndroidLab4


import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.AndroidLab4.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NewTaskSheet(private var taskItem: TaskItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        val coloredtitle = SpannableString("Название*")
        coloredtitle.setSpan(
            ForegroundColorSpan(Color.RED),
            coloredtitle.length - 1,
            coloredtitle.length,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.labelTitle.text = coloredtitle
        binding.taskTitle.text = "Добавление"
        taskViewModel = ViewModelProvider(activity)[TaskViewModel::class.java]
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.button2.setOnClickListener {
            dismiss()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return android.R.style.Theme_Material_Light_NoActionBar_Fullscreen
    }


    private fun saveAction() {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        if (name == " " || name == "") {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Ошибка!").setMessage("Введите обязательные поля!")
                .setPositiveButton("ОК") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
            builder.show()
            return
        }

        if (taskItem == null) {
            val newTask = TaskItem(name, desc)
            taskViewModel.addTaskItem(newTask)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}








