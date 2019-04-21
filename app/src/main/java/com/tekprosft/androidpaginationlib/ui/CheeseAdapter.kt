package com.tekprosft.androidpaginationlib.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tekprosft.androidpaginationlib.R
import com.tekprosft.androidpaginationlib.model.Cheese

class CheeseAdapter : PagedListAdapter<Cheese, CheeseAdapter.CheeseViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)
        return CheeseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class CheeseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mName: TextView = itemView.findViewById(R.id.name)

        fun bindTo(cheese: Cheese?){
            mName.text = cheese?.name
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem == newItem
        }
    }
}