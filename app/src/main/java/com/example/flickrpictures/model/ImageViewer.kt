package com.example.flickrpictures.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrpictures.data.Image
import com.squareup.picasso.Picasso
import com.example.flickrpictures.R
import android.content.Intent
import com.example.flickrpictures.ui.ImageDetailsActivity

class ImageViewer(val images: MutableList<Image> = mutableListOf()): RecyclerView.Adapter<ImageViewer.ImageHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val image = ImageHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image,
                parent,
                false
            )
        )
        image.itemView.setOnClickListener {
            view ->
            val intent = Intent(view.context, ImageDetailsActivity::class.java)
            val viewText = view.findViewById<TextView>(R.id.picID).text.split(":")
            intent.putExtra("ID", viewText[0])
            intent.putExtra("URL", viewText[1])
            view.context.startActivity(intent)
        }
        return image
    }



    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(images[position])
    }
    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Image) {
            val selectedView = itemView
            val selectedImage = selectedView.findViewById<ImageView>(R.id.imageView)!!
            Picasso.get().load(photo.url)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(selectedImage)
            selectedView.findViewById<TextView>(R.id.userNameView).text = photo.userID
            selectedView.findViewById<TextView>(R.id.picTags).text = photo.imageTags
            selectedView.findViewById<TextView>(R.id.picID).text = "${photo.id}:${photo.url}"
        }
    }
}
const val imageSize = 400