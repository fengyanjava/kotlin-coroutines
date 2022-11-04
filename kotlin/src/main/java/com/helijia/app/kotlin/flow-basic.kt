package com.helijia.app.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { // this: CoroutineScope
//    launch { // launch a new coroutine and continue
//        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//        println("World!") // print after delay
//    }
//    println("Hello") // main coroutine continues while a previous one is delayed

    val images = listOf("one", "two", "three")
    zipImages(images).collect {
        println("${Thread.currentThread().name} -> $it")
    }
}

fun zipImages(images: List<String>): Flow<String> {
    return images.map { image ->
        flow {
            emit("compress done: $image")
            println("do it on: ${Thread.currentThread().name}")
        }
    }.merge().flowOn(Dispatchers.IO)
}