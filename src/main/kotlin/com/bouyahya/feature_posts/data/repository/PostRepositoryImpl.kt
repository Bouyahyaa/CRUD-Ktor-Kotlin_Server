package com.bouyahya.feature_posts.data.repository

import com.bouyahya.feature_posts.data.models.Post
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import java.io.File

class PostRepositoryImpl(
    db: CoroutineDatabase
) : PostRepository {

    private val posts = db.getCollection<Post>()

    override suspend fun getPosts(): List<Post> {
        return posts.find().toList()
    }

    override suspend fun createPost(post: Post): Boolean {
        return posts.insertOne(post).wasAcknowledged()
    }

    override suspend fun updatePost(
        postId: String,
        postTitle: String,
        postImage: String
    ): Boolean {
        var post: Post? = null
        try {
            val previousPost = posts.findOne(Post::id eq postId)
            if (postImage != "") {
                post = Post(id = postId, title = postTitle, image = postImage)
                val f = File("${previousPost?.image}")
                f.delete()
            } else {
                post = Post(id = postId, title = postTitle, image = previousPost?.image!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return posts.updateOne(Post::id eq postId, post!!).wasAcknowledged()
    }

    override suspend fun deletePost(postId: String): Boolean {
        try {
            val postToDelete = posts.findOne(Post::id eq postId)
            val f = File("${postToDelete?.image}")
            f.delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return posts.deleteOneById(postId).wasAcknowledged()
    }
}