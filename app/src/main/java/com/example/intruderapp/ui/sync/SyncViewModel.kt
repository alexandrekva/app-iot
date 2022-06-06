package com.example.intruderapp.ui.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intruderapp.data.ServerSocketService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(
    private val serverSocketService: ServerSocketService
): ViewModel()  {

    init {
        viewModelScope.launch {
            serverSocketService.initSession("1")
            observeMessages()
        }
    }

    fun sendMessage(id: String, routineStart: String, routineEnd: String) {
        viewModelScope.launch {
            val msg = "DEFINIR_ROTINA_${id}_${routineStart}_${routineEnd}"
            serverSocketService.sendMessage(msg)
        }
    }

    fun observeMessages() {
        viewModelScope.launch {
            serverSocketService.observeMessages().collect {
                println(it)
            }
        }
    }

}