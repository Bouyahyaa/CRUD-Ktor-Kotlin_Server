package com.bouyahya.feature_posts.data.repository

import com.bouyahya.feature_posts.data.models.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}