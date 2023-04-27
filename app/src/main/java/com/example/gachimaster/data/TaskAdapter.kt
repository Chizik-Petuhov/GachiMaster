package com.example.gachimaster.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.gachimaster.R
import com.example.gachimaster.databinding.ItemTaskMainActivityBinding

typealias OnPressedListener = (View) -> Unit
class TaskAdapter (
    private val tascks: TaskListManegClass,
    private val onPressedListener: OnPressedListener
): BaseAdapter()
    ,View.OnClickListener
{
    override fun getCount(): Int {
        return tascks.getSize()
    }

    override fun getItem(p0: Int): Task {
        return tascks.getTask(p0)
    }

    override fun getItemId(p0: Int): Long {
        return tascks.getID(p0)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val binding = convertView?.tag as ItemTaskMainActivityBinding? ?: createBinding(parent.context)

        val task = getItem(position)

        binding.taskName.text = task.name
        binding.Readybtt.tag = task
        binding.favoritebtt.tag = task
        if (task.isDone) {
            binding.Readybtt.setImageResource(R.drawable.baseline_verified_24)
        }
        else {binding.Readybtt.setImageResource(R.drawable.baseline_radio_button_unchecked_24)}
        if (task.favorit) {
            binding.favoritebtt.setImageResource(R.drawable.baseline_star_24)
        }
        else { binding.favoritebtt.setImageResource(R.drawable.baseline_star_border_24)}
        return binding.root
    }

    override fun onClick(v: View) {
        onPressedListener.invoke(v)
    }
    private fun createBinding(context: Context): ItemTaskMainActivityBinding {
        val binding = ItemTaskMainActivityBinding.inflate(LayoutInflater.from(context))
        binding.root.tag = binding
        binding.Readybtt.setOnClickListener(this)
        binding.favoritebtt.setOnClickListener(this)
        return binding
    }
}


