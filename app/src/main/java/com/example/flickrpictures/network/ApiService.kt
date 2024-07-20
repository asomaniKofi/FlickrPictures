package com.example.flickrpictures.network

import com.example.flickrpictures.data.ImageSearchResponse
import com.example.flickrpictures.BuildConfig
import com.example.flickrpictures.data.FoundUserResponse
import com.example.flickrpictures.data.FoundTagResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=flickr.tags.getListPhoto&format=json&nojsoncallback=1&api_key=${BuildConfig.FLICKR_API_KEY}")
    suspend fun fetchTags(@Query(value = "photo_id") photoID:String): FoundTagResponse
    @GET("?method=flickr.profile.getProfile&format=json&nojsoncallback=1&api_key=${BuildConfig.FLICKR_API_KEY}")
    suspend fun fetchUser(@Query(value = "user_id") userID:String): FoundUserResponse
    // Either add the api key to a file that is not being tracked with your version control system,
    // or add a gradle script to add it as a string resource (per Google's recommendation)
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=${BuildConfig.FLICKR_API_KEY}")
    suspend fun fetchImages(@Query(value = "text") searchParam: String): ImageSearchResponse
}