package com.example.gachimaster

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.gachimaster.data.Task
import com.example.gachimaster.data.TaskAdapter
import com.example.gachimaster.data.TaskListManegClass
import com.example.gachimaster.databinding.ActivityMainBinding
import com.example.gachimaster.databinding.AddTaskDialogLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var tasks: TaskListManegClass
    private lateinit var adapter: TaskAdapter
    private var idIter: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        tasks = TaskListManegClass()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTaskListView()
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.addTasckBtt.setOnClickListener() {onAddBttPresed()}


    }

    private fun setupTaskListView() {
        adapter = TaskAdapter(tasks) {onTaskItemPressed(it)}
        binding.FoodListView.adapter = adapter
    }

    private fun onTaskItemPressed(v: View) {
        val task = v.tag as Task
        if (v.id == R.id.Readybtt){
            tasks.switchReady(task)
            adapter.notifyDataSetChanged()
            return
        }
        if (v.id == R.id.favoritebtt){
            tasks.switchFavorit(task)
            adapter.notifyDataSetChanged()
            return
        }
    }


    private fun onAddBttPresed() {
        val dialogBinding: AddTaskDialogLayoutBinding = AddTaskDialogLayoutBinding.inflate(layoutInflater)
        val dialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle("Добавить")
            .setView(dialogBinding.root)
            .setPositiveButton("OK") {d, which ->
                val task = Task()
                task.name = dialogBinding.EditTextTaskNameInDialog.text.toString()
                task.id = idIter
                idIter = idIter + 1
                tasks.addTask(task)
                adapter.notifyDataSetChanged()
            }.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}