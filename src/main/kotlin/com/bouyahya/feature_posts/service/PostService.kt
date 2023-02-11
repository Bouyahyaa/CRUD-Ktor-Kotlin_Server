package com.bouyahya.feature_posts.service

import com.bouyahya.feature_posts.data.models.Post
import com.bouyahya.feature_posts.data.repository.PostRepository

class PostService(
    private val postRepository: PostRepository
) {
    suspend fun getPosts(): List<Post> {
        return postRepository.getPosts()
    }

    suspend fun createPost(post: Post): Boolean {
        return postRepository.createPost(post)
    }

    suspend fun deletePost(postId: String): Boolean {
        return postRepository.deletePost(postId)
    }
}