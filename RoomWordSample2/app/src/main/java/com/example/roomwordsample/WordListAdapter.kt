package com.example.roomwordsample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.Entities.Word

class WordListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    interface OnWordListAdapterListener {
        fun onDeleteItem(word: Word)
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words
    var listener: OnWordListAdapterListener? = null

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val deleteWordImageView: ImageView = itemView.findViewById(R.id.deleteWord)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word
        holder.deleteWordImageView.setOnClickListener(View.OnClickListener {
            listener?.onDeleteItem(current)
        })
    }

    public fun getWordAt(position: Int): Word {
        return words.elementAt(position)
    }

    internal fun setWords(words: List<Word>) {

        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}