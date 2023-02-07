package com.bouyahya.feature_posts.service

import com.bouyahya.feature_posts.data.models.Post
import com.bouyahya.feature_posts.data.repository.PostRepository

class PostService(
    private val postRepository: PostRepository
) {
    suspend fun getPosts(): List<Post> {
        return postRepository.getPosts()
    }
}