package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TodoAdapter (
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder( itemView: View) : RecyclerView.ViewHolder( itemView )

//    private lateinit var toDoTextView : TextView
//    private lateinit var checkBox : CheckBox

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item_viewholder,
                parent,
                false
            )
        )
    }

    fun addToDo( todo: Todo ){
        todos.add(todo)
        notifyItemInserted( todos.size -1 )
    }

    fun deleteDoneToDos( ){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

   private fun toggleStrikeThrough( toDoTextView: TextView, isChecked: Boolean ) {
       if( isChecked ){
           toDoTextView.paintFlags = toDoTextView.paintFlags or STRIKE_THRU_TEXT_FLAG
       } else{
           toDoTextView.paintFlags = toDoTextView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv( )
       }
   }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = todos[position]
        holder.itemView.apply {
            val toDoTextView =  findViewById<TextView>( R.id.tvToDoTitle )
            val checkBox = findViewById<CheckBox>( R.id.DoneCheckBox )

            toDoTextView.text = currentToDo.title
            checkBox.isChecked = currentToDo.isChecked

            toggleStrikeThrough(toDoTextView, currentToDo.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(toDoTextView, isChecked)
                currentToDo.isChecked = !currentToDo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}