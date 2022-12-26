package com.geektech.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IItemClick {
  private lateinit var binding: ActivityMainBinding
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter=NoteAdapter(this)

        binding.mainRecycler.adapter=adapter

        binding.addNote.setOnClickListener {
            if(binding.editNote.text.isEmpty()) {
                binding.editNote.error = "Error"
            }
            else  {
                val note=Note(binding.editNote.text.toString(),binding.editNoteDesc.text.toString())
                adapter.addNote(note)
            binding.editNote.text.clear()
            binding.editNoteDesc.text.clear()
            }
        }
    }

    override fun delete(pos: Int) {
     adapter.delete(pos)
    }

    override fun edit(pos: Int) {
      binding.editNote.setText(adapter.getList()[pos].title)
        binding.editNoteDesc.setText(adapter.getList()[pos].desc)
        binding.addNote.text = "edit"
        binding.addNote.setOnClickListener {
            val note=Note(binding.editNote.text.toString(),binding.editNoteDesc.text.toString())
            adapter.edit(pos,note)
            binding.addNote.text = "add"
            binding.editNote.text.clear()
            binding.editNoteDesc.text.clear()
        }
    }
}