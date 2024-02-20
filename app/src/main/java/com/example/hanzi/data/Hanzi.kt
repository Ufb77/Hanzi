package com.example.hanzi.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.hanzi.R

class Hanzi(
    @DrawableRes val recursoImagen: Int,
    @StringRes val significado: Int,
    @StringRes val pronunciacion: Int) {




    val listaHanzi = listOf(
        Hanzi(R.drawable.he, R.string.significado_1, R.string.pronunciacion_1),
        Hanzi(R.drawable.mu, R.string.significado_2, R.string.pronunciacion_2),
        Hanzi(R.drawable.mu4, R.string.significado_3, R.string.pronunciacion_3),
        Hanzi(R.drawable.nu, R.string.significado_4, R.string.pronunciacion_4),
        Hanzi(R.drawable.ren, R.string.significado_5, R.string.pronunciacion_5),
        Hanzi(R.drawable.ri, R.string.significado_6, R.string.pronunciacion_6),
        Hanzi(R.drawable.shan, R.string.significado_7, R.string.pronunciacion_7),
        Hanzi(R.drawable.shui, R.string.significado_8, R.string.pronunciacion_8),
        Hanzi(R.drawable.yu, R.string.significado_9, R.string.pronunciacion_9),
        Hanzi(R.drawable.yue, R.string.significado_10, R.string.pronunciacion_10)

    )

}