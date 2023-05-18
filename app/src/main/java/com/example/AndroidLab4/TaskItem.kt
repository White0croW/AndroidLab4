package com.example.AndroidLab4

import android.content.Context
import androidx.core.content.ContextCompat
import java.util.*

class TaskItem(
    var name: String,
    var desc: String,
    var completed: Boolean=false,
    var id: UUID = UUID.randomUUID()
)
{
    fun isCompleted() = completed != null
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
}