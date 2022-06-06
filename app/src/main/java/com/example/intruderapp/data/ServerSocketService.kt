package com.example.intruderapp.data

import kotlinx.coroutines.flow.Flow

interface ServerSocketService {

    suspend fun initSession(id: String)

    suspend fun sendMessage(msg: String)

    suspend fun observeMessages(): Flow<String>
}