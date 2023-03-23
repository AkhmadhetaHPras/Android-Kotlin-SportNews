package com.aprass.sportnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprass.sportnews.databinding.ItemRowNewsBinding
import com.bumptech.glide.Glide

class ListNewsAdapter(private val listNews: ArrayList<News>): RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, date, content, image) = listNews[position]

        fun getImage(imageName: String) : Int {
            val drawableResourceId = holder.itemView.context.resources.getIdentifier(imageName, "drawable", holder.itemView.context.packageName);
            return drawableResourceId;
        }

        Glide.with(holder.itemView.context)
            .load(getImage(image))
            .into(holder.binding.imgItemImage);
        holder.binding.tvItemTitle.text = title
        holder.binding.tvItemDate.text = date
        holder.binding.tvItemContent.text = content

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listNews[holder.adapterPosition]) }
    }



}