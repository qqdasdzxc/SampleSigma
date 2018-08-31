package com.dmitrijkuzmin.samplesigma.ui.view

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dmitrijkuzmin.samplesigma.R
import com.dmitrijkuzmin.samplesigma.model.entities.Item
import kotlin.collections.ArrayList

class MainAdapter : RecyclerView.Adapter<MainAdapter.ItemHolder>() {

    var items: ArrayList<Item> = ArrayList()

    fun setItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holder, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val creator: TextView = itemView.findViewById(R.id.creator)
        val pubDate: TextView = itemView.findViewById(R.id.pubdate)
        val description: TextView = itemView.findViewById(R.id.description)
        val categories: TextView = itemView.findViewById(R.id.categories)

        fun setData(item: Item) {
            title.text = item.title
            creator.text = item.creator
            pubDate.text = String.format("(%s)", item.pubDate)
            description.text = Html.fromHtml(item.description)
            categories.text = item.categoriesToString()
        }
    }

}