package com.example.hanzi

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hanzi.datasource.RepositorioHanzi
import com.example.hanzi.ui.theme.HanziTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HanziTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Diccionario(context = LocalContext.current)
                }
            }
        }
    }
}

@Composable
fun Diccionario(context: Context){
val misHanzi = RepositorioHanzi().getHanzi()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BarraSuperior()})
    {innerPadding->
        
        val hanzis = misHanzi
        MostrarListaHanzi(lista = hanzis, contentPadding = innerPadding )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior() {

    CenterAlignedTopAppBar(title = {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(painterResource(id = R.drawable.caracter), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size1))
                    .clip(CircleShape))
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.Spacer1)))
            Text(text = "Diccionario " + stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge)

        }

            //style = MaterialTheme.typography.displayLarge)
    })
}


@Preview
@Composable
fun PreviewTotal(){
    HanziTheme(darkTheme = false) {
        Diccionario(context = LocalContext.current)
    }
}

@Preview
@Composable
fun PreviewTotal1(){

    HanziTheme(darkTheme = true) {
        Diccionario(context = LocalContext.current)
    }
}
