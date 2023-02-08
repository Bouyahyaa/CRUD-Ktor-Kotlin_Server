package com.bouyahya.feature_posts.routes

import com.bouyahya.feature_posts.data.models.Post
import com.bouyahya.feature_posts.service.PostService
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

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

        post("/") {
            val multipartData = call.receiveMultipart()
            var title = ""
            var image = ""
            var fileName = ""
            var name = ""
            var extension = ""

            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "title" -> {
                                title = part.value
                            }
                        }
                    }

                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        val pos: Int = fileName.lastIndexOf(".")
                        name = fileName.substring(0, pos)
                        extension = fileName.substring(pos)
                        val date = System.currentTimeMillis() + 600000
                        image = "http://192.168.1.19:8080/uploads/${name + date + extension}"
                        File("uploads/${name + date + extension}").writeBytes(fileBytes)
                    }

                    else -> {}
                }
            }

            val post = Post(title = title, image = image)
            val isCreated = postService.createPost(post)

            if (isCreated) {
                call.respond(HttpStatusCode.Created, "Post Created Successfully")
            } else {
                call.respond(HttpStatusCode.Conflict, "Post not created")
            }
        }
    }
}