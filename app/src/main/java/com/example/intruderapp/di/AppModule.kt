package com.example.intruderapp.di

import com.example.intruderapp.data.ServerSocketService
import com.example.intruderapp.data.ServerSocketServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation)
        }
    }

    @Provides
    @Singleton
    fun provideServerSocketService(client: HttpClient): ServerSocketService {
        return ServerSocketServiceImpl(client)
    }
}