package com.bouyahya

import com.bouyahya.feature_posts.data.repository.PostRepositoryImpl
import com.bouyahya.feature_posts.service.PostService
import io.ktor.server.application.*
import com.bouyahya.plugins.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val databaseName = "KtorKotlinCrudDB"
    val connectionString = "" // Replace This
    val db = KMongo.createClient(
        connectionString = connectionString
    ).coroutine.getDatabase(databaseName)

    val postRepository = PostRepositoryImpl(db)
    val postService = PostService(postRepository)

    configureSerialization()
    configureHTTP()
    configureMonitoring()
    configureSecurity()
    configureRouting(postService = postService)
}
