package com.example.AndroidLab4

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.AndroidLab4.databinding.CurrentFragmentCardBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CardTask(var taskItem: TaskItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: CurrentFragmentCardBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        val coloredtitle = SpannableString("Название *")
        coloredtitle.setSpan(
            ForegroundColorSpan(Color.RED),
            coloredtitle.length.toInt() - 1,
            coloredtitle.length.toInt(),
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )

        val bcomplete = binding.completeButton
        val editable = Editable.Factory.getInstance()
        bcomplete.text = if (taskItem?.completed == true) "Отменить выполнение" else "Выполнить"
        binding.name.text = editable.newEditable(taskItem!!.name)
        binding.desc.text = editable.newEditable(taskItem!!.desc)
        binding.name.setEnabled(false)
        binding.desc.setEnabled(false)
        bcomplete.setOnClickListener {
            taskViewModel.setCompleted(taskItem!!)
            if (taskItem!!.completed) {

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Сообщение")
                    .setMessage("Поздравляем. Задача выполнена")
                    .setPositiveButton("ОК") { dialog, id ->
                        dialog.cancel()
                        dismiss()
                    }
                builder.create()
                builder.show()
            }
        }
        binding.button2.setOnClickListener {
            dismiss()
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentFragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun getTheme(): Int {
        return android.R.style.Theme_Material_Light_NoActionBar_Fullscreen
    }
}