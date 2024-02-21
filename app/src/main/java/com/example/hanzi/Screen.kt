package com.example.hanzi

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hanzi.modelo.Hanzi
import com.example.hanzi.datasource.RepositorioHanzi
import com.example.hanzi.ui.theme.HanziTheme


@Composable
fun Tarjeta(hanzi: Hanzi, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.padding_small))
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.redondeoEsquinas)))) {


        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            Image(painter = painterResource(id = hanzi.recursoImagen),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.sizeImagenHanzi))
                    .clip(CircleShape)
                    .background(colorResource(R.color.personalizado)))

            BotonExpandirCard(expandido = expanded, onClick = { expanded = !expanded })


            if(expanded) {
                DesplegableSignificadoPronunciacion(hanzi = hanzi)
            }


        }
    }



}


@Composable
private fun BotonExpandirCard(expandido: Boolean,
                              onClick: () -> Unit,
                              modifier: Modifier = Modifier
){

    IconButton(
        onClick = onClick,
        modifier = modifier) {
        Icon(
            imageVector =if(expandido) Icons.Filled.ChevronLeft else Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(dimensionResource(id = R.dimen.sizeIcono)
            ))

    }

}

@Composable
fun DesplegableSignificadoPronunciacion(hanzi: Hanzi,
                                        modifier: Modifier = Modifier) {

    //Vigila el estado del desvanecimiento
    var desvanecimiento by remember { mutableStateOf(false) }


    val valorAlpha by animateFloatAsState(
        targetValue = if (desvanecimiento) 1f else 0f, //1f->opaco.  0f->transparente
        animationSpec = tween(durationMillis = 450), //Indica que la animación es lineal con duracion (X)
        label = "Transparencia"
    )

    //Cuando hay variación en desvanecimiento, cambia su valor
    LaunchedEffect(desvanecimiento) {
        if (!desvanecimiento) {
            desvanecimiento = true
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(
                horizontal = dimensionResource(id = R.dimen.paddingFilaHorizontal),
                vertical = dimensionResource(id = R.dimen.paddingFilaVertical)
            )
            .alpha(valorAlpha), //Transparencia que puede variar

        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = hanzi.significado),
            style = MaterialTheme.typography.bodyLarge,
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(horizontal = dimensionResource(id = R.dimen.paddingFilaHorizontal),
                vertical = dimensionResource(id = R.dimen.paddingFilaVertical))
            .alpha(valorAlpha),//Transparencia que puede variar

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = hanzi.pronunciacion),
            style = MaterialTheme.typography.displayMedium,
        )
    }
}

@Composable
fun MostrarListaHanzi(lista: List<Hanzi>,
                      modifier: Modifier = Modifier,
                      contentPadding : PaddingValues = PaddingValues(0.dp) ){

    LazyColumn(
        contentPadding = contentPadding
    ){
        items(lista){
            Tarjeta(hanzi = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaPreview() {
    HanziTheme {
        val hanzi1 = Hanzi(R.drawable.he, R.string.significado_1, R.string.pronunciacion_1)
        MostrarListaHanzi(lista = RepositorioHanzi().getHanzi())
        Tarjeta(hanzi1)
    }
}