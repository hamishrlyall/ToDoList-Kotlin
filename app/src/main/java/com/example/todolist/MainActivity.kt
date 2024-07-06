package com.example.todolist

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity( ) {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        val toDoItemsRecyclerView = findViewById<RecyclerView>(R.id.toDoItemsRecyclerView)
        toDoItemsRecyclerView.adapter = todoAdapter
        toDoItemsRecyclerView.layoutManager = LinearLayoutManager(this)

        val toDoEditText = findViewById<EditText>(R.id.toDoItemAddEditText)
        val addToDoButton = findViewById<Button>(R.id.AddToDoButton)
        val deleteToDoButton = findViewById<Button>(R.id.RemoveToDoButton)

        addToDoButton.setOnClickListener {
            val toDoTitle = toDoEditText.text.toString()
            if (toDoTitle.isNotEmpty()) {
                val todo = Todo(toDoTitle)
                todoAdapter.addToDo(todo)
                toDoEditText.text.clear()
            }
        }

        deleteToDoButton.setOnClickListener {
            todoAdapter.deleteDoneToDos()
        }

        toDoEditText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    val toDoTitle = toDoEditText.text.toString()

                    if (toDoTitle.isNotEmpty()) {
                        val todo = Todo(toDoTitle)
                        todoAdapter.addToDo(todo)
                        toDoEditText.text.clear()
                    }
                    true
                }
                else -> false
            }
        }
    }
}