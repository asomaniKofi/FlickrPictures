package com.example.flickrpictures.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrpictures.data.Image
import com.example.flickrpictures.model.ImageModel
import com.example.flickrpictures.model.ImageViewer
import com.example.flickrpictures.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val searchDelay = 200L

class ImageView: AppCompatActivity(){
    private val imageModel: ImageModel by viewModels()
    private val viewer = ImageViewer()

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        val finder = findViewById<EditText>(R.id.imageSearch)
        val recycleViewer = findViewById<RecyclerView>(R.id.imageRecyclerView)

        finder.addTextChangedListener { watcher ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(searchDelay)
                val list = imageModel.fetchImages(watcher.toString())
                with(viewer) {
                    images.clear()
                    images.addAll(list)
                    notifyDataSetChanged()
                }
            }
        }

        recycleViewer.adapter = viewer
        recycleViewer.layoutManager = GridLayoutManager(this, 3)
    }
}