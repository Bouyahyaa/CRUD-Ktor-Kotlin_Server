package com.bouyahya.feature_posts.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.uploadRoutes() {

    route("/uploads/{image}") {

        get {
            val image = call.parameters["image"]
            val file = File("uploads/${image}").readBytes()
            call.respond(file)
        }
    }
}