package com.example.flickrpictures.data

data class ImageSearchResponse(
    val photos: ImageData
)

data class FoundUserResponse (
    val profile: User
)


data class User (
    val id: String,
    val nsid: String,
    val join_date: String,
    val occupation: String,
    val hometown: String,
    val showcase_set: String,
    val showcase_set_title: String,
    val first_name: String,
    val last_name: String,
    val profile_description: String,
    val city: String,
    val country: String,
    val facebook: String,
    val twitter: String,
    val tumblr: String,
    val instagram: String,
    val pinterest: String
)

data class ImageData(
    val page: Int,
    val photo: List<ImageResponse>
)

data class ImageResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)

data class Image(
    val id:String,
    val url:String,
    val title: String,
    val user: User
)