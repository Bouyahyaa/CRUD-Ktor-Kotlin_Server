package com.bouyahya.feature_posts.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Post(
    @BsonId
    val id: String? = ObjectId().toString(),
    val title: String,
    val image: String,
)