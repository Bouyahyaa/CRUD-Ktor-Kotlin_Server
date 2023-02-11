package com.bouyahya.plugins

import com.bouyahya.feature_posts.routes.posts
import com.bouyahya.feature_posts.routes.uploadRoutes
import com.bouyahya.feature_posts.service.PostService
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting(
    postService: PostService
) {
    routing {
        posts(postService = postService)
        uploadRoutes()
    }
}
