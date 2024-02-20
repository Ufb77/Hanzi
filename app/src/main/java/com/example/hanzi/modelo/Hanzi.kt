package com.example.hanzi.modelo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hanzi(
    @DrawableRes val recursoImagen: Int,
    @StringRes val significado: Int,
    @StringRes val pronunciacion: Int)






