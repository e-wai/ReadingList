package com.example.readinglist.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.readinglist.R

class AddActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var entryText: EditText
    lateinit var spinner: Spinner
    lateinit var confirmBtn: Button

    var type: String = "NOTE" //default to note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        entryText = findViewById(R.id.edit_word)

        spinner = findViewById<Spinner>(R.id.choose_type_spinner)
        ArrayAdapter.createFromResource(this,
            R.array.entry_types,
            android.R.layout.simple_spinner_item)
            .also {
                it.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                spinner.adapter = it
            }

        supportActionBar?.hide()

        confirmBtn = findViewById<Button>(R.id.add_button)
        confirmBtn.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(entryText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_VALUE, entryText.text.toString())
                replyIntent.putExtra(EXTRA_TYPE, type)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_VALUE = "com.example.readinglist.REPLY"
        const val EXTRA_TYPE = "com.example.readinglist.TYPE"
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        type = resources.getStringArray(R.array.entry_types)[position].toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        type = "NOTE"
    }
}