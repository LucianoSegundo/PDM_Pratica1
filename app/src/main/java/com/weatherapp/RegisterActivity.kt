package com.weatherapp

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
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

@OptIn(ExperimentalMaterial3Api::class)
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
                    //a
                    Firebase.auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(activity!!) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(activity,"Registro OK!", Toast.LENGTH_LONG).show()
                                activity.startActivity(
                                    Intent(activity, MainActivity::class.java).setFlags(
                                        FLAG_ACTIVITY_SINGLE_TOP )
                                )
                            } else {
                                Toast.makeText(activity,
                                    "Registro FALHOU!", Toast.LENGTH_LONG).show()
                            }
                        }

                },
                enabled =  nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty() && confSenha.isNotEmpty() && senha.equals(confSenha)
            ) {
                Text("Registrar")
            }
        }

    }
}

