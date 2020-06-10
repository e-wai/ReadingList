package com.example.readinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.readinglist.model.Entry

class EntryListAdapter(context: Context, onCardClickListener: OnCardClickListener) : RecyclerView.Adapter<EntryListAdapter.EntryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var entries = emptyList<Entry>() // Cached copy of words
    var onCardClickListener: OnCardClickListener = onCardClickListener

    val context: Context = context

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val entryItemView: TextView = itemView.findViewById(R.id.textView)
        init {
            entryItemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onCardClickListener.onCardClicked(entries[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView = inflater.inflate(R.layout.entry_layout, parent, false)
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val current = entries[position]
        holder.entryItemView.text = current.value
    }

    internal fun setEntries(entries: List<Entry>) {
        this.entries = entries
        notifyDataSetChanged()
    }

    override fun getItemCount() = entries.size

    interface OnCardClickListener {
        fun onCardClicked(entryToDelete: Entry)
    }
}