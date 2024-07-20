package com.example.flickrpictures.data

data class ImageSearchResponse(
    val photos: ImageData
)

data class FoundUserResponse (
    val profile: User
)

data class FoundTagResponse (
    val photo:TagResponse
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

data class TagResponse(
    val id:String,
    val tags: Tag
)

data class Tag (
    val tag: List<TagInfo>
)
data class TagInfo (
    val id:String,
    val author:String,
    val authorname:String,
    val raw:String,
    val _content:String,
)
data class Image(
    val id:String,
    val url:String,
    val title: String,
    val userID: String,
    val imageTags: String
)