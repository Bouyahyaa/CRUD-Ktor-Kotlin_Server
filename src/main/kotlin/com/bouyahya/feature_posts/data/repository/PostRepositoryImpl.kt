package com.bouyahya.feature_posts.data.repository

import com.bouyahya.feature_posts.data.models.Post
import org.litote.kmongo.coroutine.CoroutineDatabase

class PostRepositoryImpl(
    db: CoroutineDatabase
) : PostRepository {

    private val posts = db.getCollection<Post>()

    override suspend fun getPosts(): List<Post> {
        return posts.find().toList()
    }

    override suspend fun createPost(post : Post): Boolean {
        return posts.insertOne(post).wasAcknowledged()
    }
}