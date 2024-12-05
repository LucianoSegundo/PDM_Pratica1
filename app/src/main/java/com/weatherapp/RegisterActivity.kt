package com.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Registro(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Registro( modifier: Modifier = Modifier) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var confSenha by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =  modifier.padding(8.dp)
    ) {

    Text(
        text = "Registro",

    )
        OutlinedTextField(
            value = nome,
            label = { Text(text = "Nome") },
            modifier = modifier.fillMaxWidth(),
            onValueChange = { nome = it}

        )

        OutlinedTextField(
            value =  email,
            label =  { Text(text = "Email") },
            modifier =  modifier.fillMaxWidth(),
            onValueChange =  {email = it}
        )

        OutlinedTextField(
            value = senha,
            label = { Text(text = "Senha") },
            modifier =  modifier.fillMaxWidth(),
            onValueChange = {senha = it},
            visualTransformation = PasswordVisualTransformation()

        )

        OutlinedTextField(
            value = confSenha,
            label = { Text(text = "Confirmar Senha") },
            modifier = modifier.fillMaxWidth(),
            onValueChange = {confSenha= it},
            visualTransformation = PasswordVisualTransformation()

        )

        Row {
            Button(
                onClick = {
                    Toast.makeText(activity, "Voltar", Toast.LENGTH_LONG).show()

                    activity?.finish()
                },
            ){
                Text("voltar")
            }
            Spacer(modifier = modifier.size(24.dp))

            Button(onClick = {
                senha= "";
                nome = "";
                email ="";
                confSenha ="";

                Toast.makeText(activity, "formulario Limpo", Toast.LENGTH_SHORT).show()
            }) {
                Text("Limpar")
            }
            Spacer(modifier = modifier.size(24.dp))


            Button(
                onClick = {
                    Toast.makeText(activity, "Registrado com sucesso", Toast.LENGTH_LONG).show()

                    activity?.finish()
                },
                enabled =  nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty() && confSenha.isNotEmpty() && senha.equals(confSenha)
            ) {
                Text("Registrar")
            }
        }

    }
}

