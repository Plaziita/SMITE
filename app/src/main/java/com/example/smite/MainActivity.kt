package com.example.smite

import SMITETheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smite.data.Dios
import com.example.smite.datasource.Datasource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMITETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SmiteApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmiteApp(){
    var listaDioses by remember { mutableStateOf(listOf<Dios>()) }

    Scaffold(
        topBar = {
            SmiteTopBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            Datasource().getDioses { listaDioses = it }

            items( listaDioses ) {
                DiosItem(
                    dios = it,
                    modifier = Modifier
                )
            }

        }
    }
}

@Preview
@Composable
fun SMITEPreview(){
    SMITETheme {
        SmiteApp()
    }
}

@Preview
@Composable
fun SMITEDarkThemePreview() {
    SMITETheme(darkTheme = true) {
        SmiteApp()
    }
}
