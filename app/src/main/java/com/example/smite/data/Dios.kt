package com.example.smite.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dios(
    @StringRes val nombre: Int,
    @StringRes val lore: Int,
    @DrawableRes val avatar: Int,
    @DrawableRes val pasiva: Int,
    @DrawableRes val rol: Int,
    @StringRes val roltxt: Int,
    @DrawableRes val tipo: Int,
    @StringRes val tipotxt: Int,
    @DrawableRes val ataque: Int,
    @StringRes val ataquetxt: Int,
    @DrawableRes val dmg: Int,
    @StringRes val dmgtxt: Int,
    @StringRes val apodo: Int,
    @StringRes val descripcionPasiva: Int,
    @DrawableRes val habilidad1: Int,
    @StringRes val descripcionHabilidad1: Int,
    @DrawableRes val habilidad2: Int,
    @StringRes val descripcionHabilidad2: Int,
    @DrawableRes val habilidad3: Int,
    @StringRes val descripcionHabilidad3: Int,
    @DrawableRes val habilidad4: Int,
    @StringRes val descripcionHabilidad4: Int,
    )
