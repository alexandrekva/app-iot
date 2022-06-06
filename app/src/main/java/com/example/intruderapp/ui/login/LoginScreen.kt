package com.example.intruderapp.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intruderapp.navigation.Screens

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Login", style = MaterialTheme.typography.h4)

        Column() {

            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Usuário") })

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Senha") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }


        Button(onClick = {
            if (loginViewModel.validateLogin(username = user, password = password)) {
                navController.popBackStack()
                navController.navigate(Screens.Sync.route)
            } else {
                Toast.makeText(context, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login")
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewLoginScreen() {
    LoginScreen(rememberNavController())
}