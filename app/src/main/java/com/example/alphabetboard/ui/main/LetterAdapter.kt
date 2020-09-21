package com.example.alphabetboard.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alphabetboard.R
import kotlinx.android.synthetic.main.item_letter_tile.view.*

class LetterAdapter(private val dimen : Pair<Int, Int>) : ListAdapter<LetterTile, LetterAdapter.ViewHolder>(DiffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_letter_tile, parent, false)
        val lParam = ConstraintLayout.LayoutParams(dimen.first, dimen.second)
        itemView.layoutParams = lParam
//        d("onCreateViewHolder")
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        d("onBinfViewHolder")

        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(letter: LetterTile) = with(itemView) {
            letter_tv.text = letter.letter.toString()
            hand_tv.text = letter.hand.toString()
        }
    }


    companion object DiffUtilCallback : DiffUtil.ItemCallback<LetterTile>() {
        override fun areItemsTheSame(oldItem: LetterTile, newItem: LetterTile) =
            oldItem.letter == newItem.letter

        override fun areContentsTheSame(oldItem: LetterTile, newItem: LetterTile) =
            oldItem == newItem
    }
}
