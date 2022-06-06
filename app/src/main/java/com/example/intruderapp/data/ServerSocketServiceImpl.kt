package com.example.intruderapp.data

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import java.lang.Exception

class ServerSocketServiceImpl(
    private val client: HttpClient
) : ServerSocketService {

    private var socket: WebSocketSession? = null

    override suspend fun initSession(id: String) {
        try {
            socket = client.webSocketSession {
                url("${Endpoints.serverSocket.url}?id=$id")
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }

    override suspend fun sendMessage(msg: String) {
        try {
            socket?.send(msg)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override suspend fun observeMessages(): Flow<String> {
        while (true) {
            socket?.incoming?.consumeEach { frame ->
                return if (frame is Frame.Text) {
                    val msg = frame.readText()
                    flow { msg }
                } else {
                    flow { }
                }
            }
        }
    }

    companion object {
        const val BASE_URL = "ws://192.168.86.198:8082"
    }

    sealed class Endpoints(val url: String) {
        object serverSocket : Endpoints("$BASE_URL/server-socket")
    }
}