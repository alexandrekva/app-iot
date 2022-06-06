package com.example.intruderapp.ui.sync

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intruderapp.navigation.Screens

@Composable
fun SyncScreen(
    navController: NavHostController,
    syncViewModel: SyncViewModel = hiltViewModel()
) {
    var systemId by remember { mutableStateOf("") }
    var routineStart by remember { mutableStateOf("") }
    var routineEnd by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Adicionar sistema", style = MaterialTheme.typography.h4)


        Column() {
            OutlinedTextField(
                value = systemId,
                onValueChange = { systemId = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "ID do sistema a ser adicionado") })

            OutlinedTextField(
                value = routineStart,
                onValueChange = { routineStart = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Hora de início da rotina") })

            OutlinedTextField(
                value = routineEnd,
                onValueChange = { routineEnd = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Hora de termino da rotina") })
        }

        Button(onClick = {
            syncViewModel.sendMessage(
                id = systemId,
                routineStart = routineStart,
                routineEnd = routineEnd
            )
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Adicionar")
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewSyncScreen() {
    SyncScreen(rememberNavController())
}