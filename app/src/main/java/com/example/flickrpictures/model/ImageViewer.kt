package com.example.flickrpictures.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrpictures.data.Image
import com.squareup.picasso.Picasso
import com.example.flickrpictures.R

class ImageViewer(val images: MutableList<Image> = mutableListOf()): RecyclerView.Adapter<ImageViewer.ImageHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(images[position])
    }
    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Image) {
            Picasso.get().load(photo.url)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(itemView.findViewById<ImageView>(R.id.imageView)!!)

        }
    }
}
const val imageSize = 400