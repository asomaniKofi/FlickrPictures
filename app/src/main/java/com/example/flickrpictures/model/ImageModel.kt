package com.example.flickrpictures.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrpictures.data.Image
import com.example.flickrpictures.network.WebClient
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.flickrpictures.data.FoundImageDetails

class ImageModel: ViewModel() {
    suspend fun fetchImageDetails(photoID:String): FoundImageDetails {
        return WebClient.client.fetchDetails(photoID)
    }
    suspend fun fetchImages(searchParam:String): List<Image> {
        if(searchParam.isBlank()){
            return emptyList()
        }

        val response = WebClient.client.fetchImages(searchParam)

        return response.photos.photo.map { image ->
            val photoTags = WebClient.client.fetchTags(image.id)
            var tags = ""
            for(tag in photoTags.photo.tags.tag){
                tags += tag.raw
                tags += ", "
            }
            Image(
                id = image.id,
                url = "https://farm${image.farm}.staticflickr.com/${image.server}/${image.id}_${image.secret}.jpg",
                title = image.title,
                userID = image.owner,
                imageTags = tags
            )
        }
    }
}