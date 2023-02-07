package com.bouyahya.feature_posts.routes

import com.bouyahya.feature_posts.service.PostService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.posts(
    postService: PostService
) {
    route("/api/posts") {
        get("/") {
            val posts = postService.getPosts()
            call.respond(
                HttpStatusCode.OK, posts
            )
        }
    }
}