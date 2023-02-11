package com.bouyahya.feature_posts.data.repository

import com.bouyahya.feature_posts.data.models.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun createPost(post: Post): Boolean
    suspend fun deletePost(postId: String): Boolean
}