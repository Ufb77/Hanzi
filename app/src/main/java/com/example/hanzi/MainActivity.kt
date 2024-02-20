package com.example.hanzi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hanzi.modelo.Hanzi
import com.example.hanzi.modelo.RepositorioHanzi
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
                    MostrarListaHanzi(lista = RepositorioHanzi.listaHanzi)
                }
            }
        }
    }
}

@Composable
fun Card(hanzi: Hanzi, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }
    Row (modifier = Modifier.fillMaxWidth()){

        Image(painter = painterResource(id = hanzi.recursoImagen), contentDescription = null,
            modifier.size(50.dp))
        BotonExpandirCard(expandido = expanded, onClick = { expanded = !expanded })
        //Spacer(modifier = )

        if(expanded) {
            DesplegableSignificadoPronunciacion(hanzi = hanzi)
        }


    }
}


@Composable
 private fun BotonExpandirCard(expandido: Boolean,
                              onClick: () -> Unit,
                              modifier: Modifier = Modifier){

    IconButton(
        onClick = onClick,
        modifier = modifier) {
        androidx.compose.material3.Icon(
            imageVector =if(expandido) Icons.Filled.ChevronLeft else Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary)

    }

}

@Composable
fun DesplegableSignificadoPronunciacion(hanzi: Hanzi,
                                        modifier: Modifier = Modifier){
    Row(modifier = Modifier.animateContentSize(animationSpec = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    ))) {
        Row (Modifier.fillMaxWidth(0.7f)){
            Text(text = stringResource(id = hanzi.significado), fontSize =  30.sp)
        }


        Row(Modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.Start) {
            Text(text = stringResource(id = hanzi.pronunciacion), fontSize = 30.sp)
        }



    }

}

@Composable
fun MostrarListaHanzi(lista: List<Hanzi>, modifier: Modifier = Modifier){
    LazyColumn(){
        items(lista){
            Card(hanzi = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HanziTheme {
        val hanzi1 = Hanzi(R.drawable.he, R.string.significado_1, R.string.pronunciacion_1)
        MostrarListaHanzi(lista = RepositorioHanzi.listaHanzi)
       Card(hanzi1)
    }
}