package com.example.readinglist.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readinglist.model.Entry
import com.example.readinglist.EntryListAdapter
import com.example.readinglist.EntryViewModel
import com.example.readinglist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), EntryListAdapter.OnCardClickListener {

    private val addEntryRequestCode = 1
    private lateinit var model: EntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = EntryListAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        model = ViewModelProvider(this).get(EntryViewModel::class.java)
        model.allEntries.observe(this, Observer { entries ->
            entries?.let { adapter.setEntries(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivityForResult(intent, addEntryRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addEntryRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddActivity.EXTRA_VALUE)?.let {
                model.insert(
                    Entry(
                        it,
                        AddActivity.EXTRA_TYPE
                    )
                )
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCardClicked(entryToDelete: Entry) {
        AlertDialog.Builder(this).setTitle("Delete entry?")
            .setPositiveButton("Delete") {_, _ -> model.delete(entryToDelete) }
            .show()
    }
}
