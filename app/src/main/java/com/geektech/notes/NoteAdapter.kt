package com.geektech.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.notes.databinding.ItemNoteBinding

class NoteAdapter(val listener: IItemClick) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var list: MutableList<Note> = ArrayList()

    fun addNote(note: Note) {
        list.add(note)
        notifyItemInserted(list.size)
    }

    fun delete(pos: Int) {
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }
 fun getList(): MutableList<Note>{
     return list
 }
    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note){
            binding.itemText.text = note.title
            binding.itemTextDesc.text = note.desc
            binding.root.setOnLongClickListener {
                listener.delete(adapterPosition)
                return@setOnLongClickListener true
            }
            binding.root.setOnClickListener {
                listener.edit(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun edit(pos: Int, note: Note) {
  list.set(pos,note)
        notifyItemChanged(pos)
    }
}