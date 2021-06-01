package com.techtrickbd.learnkotlin.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.techtrickbd.learnkotlin.R
import com.techtrickbd.learnkotlin.interfaces.CellClickListener
import com.techtrickbd.learnkotlin.models.Blogs
import kotlinx.android.synthetic.main.sample_layout.view.*

class BlogsAdapter(
    private val bloglist: List<Blogs>, private val context: Context
) :
    RecyclerView.Adapter<BlogsAdapter.BlogViewHolder>() {

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.featured_image
        val title: TextView = itemView.title
        val desc: TextView = itemView.desc

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.sample_layout, parent, false
        )
        return BlogViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bloglist.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val currentItem = bloglist[position]
        //Glide.with(context).load(currentItem.featuredImages!!).into(holder.imageView)
        holder.title.text = currentItem.title
        holder.desc.text = currentItem.desc

        if (position == 0) {
            holder.title.setBackgroundColor(Color.YELLOW)
        }

    }
}