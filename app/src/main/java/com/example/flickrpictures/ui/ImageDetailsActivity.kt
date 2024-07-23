package com.example.flickrpictures.ui

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView

import android.os.Bundle
import android.widget.TableLayout
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flickrpictures.R
import com.example.flickrpictures.model.ImageModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.alclabs.fasttablelayout.FastTableLayout
class ImageDetailsActivity: AppCompatActivity() {
    private val imageModel: ImageModel by viewModels()
    private var detailJob: Job? = null
    private var imageID: String = ""
    private var imageURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagedetails)
        imageID = intent.getStringExtra("ID")!!
        imageURL = intent.getStringExtra("URL")!!
        Picasso.get().load(imageURL)
            .resize(
                imageSize,
                imageSize
            )
            .centerCrop()
            .into(findViewById<ImageView>(R.id.selectedImageView)!!)
getDataAndPopulateTable()
    }

    private fun getDataAndPopulateTable() {
        detailJob?.cancel()
        detailJob =  lifecycleScope.launch {
            val details = imageModel.fetchImageDetails(imageID).photo

            val headers = arrayOf("Detail","Info")
            val data = Array<Array<String>>(2) {arrayOf()}

            data[0] = arrayOf("ID",details.id)
            data[1] = arrayOf("Date",details.dateuploaded)

            val fastTable = FastTableLayout(this@ImageDetailsActivity,findViewById<TableLayout>(R.id.tableLayout)!!,headers, data )
            fastTable.build()
        }
    }
}

const val imageSize = 500