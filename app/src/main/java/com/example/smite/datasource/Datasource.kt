package com.example.smite.datasource

import android.util.Log
import com.example.smite.data.Dios
import com.google.firebase.firestore.FirebaseFirestore

class Datasource {

    private val TAG = "DataSource"

    private val COLECCION_DIOSES = "dioses"
    private val CAMPO_APODO = "apodo"
    private val CAMPO_ATAQUE = "ataque"
    private val CAMPO_ATAQUETXT = "ataquetxt"
    private val CAMPO_AVATAR = "avatar"
    private val CAMPO_DESCRIPCIONH1 = "descripcionHabilidad1"
    private val CAMPO_DESCRIPCIONH2 = "descripcionHabilidad2"
    private val CAMPO_DESCRIPCIONH3 = "descripcionHabilidad3"
    private val CAMPO_DESCRIPCIONH4 = "descripcionHabilidad4"
    private val CAMPO_DMG = "dmg"
    private val CAMPO_DMGTXT = "dmgtxt"
    private val CAMPO_DESCRIPCIONPASIVA = "descripcionPasiva"
    private val CAMPO_HABILIDAD1 = "habilidad1"
    private val CAMPO_HABILIDAD2 = "habilidad2"
    private val CAMPO_HABILIDAD3 = "habilidad3"
    private val CAMPO_HABILIDAD4 = "habilidad4"
    private val CAMPO_LORE = "lore"
    private val CAMPO_NOMBRE = "nombre"
    private val CAMPO_PASIVA = "pasiva"
    private val CAMPO_ROL = "rol"
    private val CAMPO_ROLTXT = "roltxt"
    private val CAMPO_TIPO = "tipo"
    private val CAMPO_TIPOTXT = "tipotxt"

    private val db = FirebaseFirestore.getInstance()

    private val dioses = db.collection(COLECCION_DIOSES)

    fun getDioses(setLista: (List<Dios>) -> Unit) {

        val listaDioses = mutableListOf<Dios>()
        dioses.get().addOnSuccessListener { documents ->

            for (document in documents) {
                val nombre = document.getString(CAMPO_NOMBRE) ?: ""
                val lore = document.getString(CAMPO_LORE) ?: ""
                val avatar = document.getString(CAMPO_AVATAR) ?: ""
                val pasiva = document.getString(CAMPO_PASIVA) ?: ""
                val rol = document.getString(CAMPO_ROL) ?: ""
                val roltxt = document.getString(CAMPO_ROLTXT) ?: ""
                val tipo = document.getString(CAMPO_TIPO) ?: ""
                val tipotxt = document.getString(CAMPO_TIPOTXT) ?: ""
                val ataque = document.getString(CAMPO_ATAQUE) ?: ""
                val ataquetxt = document.getString(CAMPO_ATAQUETXT) ?: ""
                val dmg = document.getString(CAMPO_DMG) ?: ""
                val dmgtxt = document.getString(CAMPO_DMGTXT) ?: ""
                val apodo = document.getString(CAMPO_APODO) ?: ""
                val descripcionPasiva = document.getString(CAMPO_DESCRIPCIONPASIVA) ?: ""
                val habilidad1 = document.getString(CAMPO_HABILIDAD1) ?: ""
                val descripcionHabilidad1 = document.getString(CAMPO_DESCRIPCIONH1) ?: ""
                val habilidad2 = document.getString(CAMPO_HABILIDAD2) ?: ""
                val descripcionHabilidad2 = document.getString(CAMPO_DESCRIPCIONH2) ?: ""
                val habilidad3 = document.getString(CAMPO_HABILIDAD3) ?: ""
                val descripcionHabilidad3 = document.getString(CAMPO_DESCRIPCIONH3) ?: ""
                val habilidad4 = document.getString(CAMPO_HABILIDAD4) ?: ""
                val descripcionHabilidad4 = document.getString(CAMPO_DESCRIPCIONH4) ?: ""

                //Creamos un objeto cita y lo añadimos a la lista
                val dios = Dios(
                    nombre,
                    lore,
                    avatar,
                    pasiva,
                    rol,
                    roltxt,
                    tipo,
                    tipotxt,
                    ataque,
                    ataquetxt,
                    dmg,
                    dmgtxt,
                    apodo,
                    descripcionPasiva,
                    habilidad1,
                    descripcionHabilidad1,
                    habilidad2,
                    descripcionHabilidad2,
                    habilidad3,
                    descripcionHabilidad3,
                    habilidad4,
                    descripcionHabilidad4
                )
                listaDioses.add(dios)

                //Escribimos una línea en el LOG para verificar la lectura correcta de los datos
                Log.i(TAG, "------> Dios: $nombre")
            }
            setLista(listaDioses)
        }
        }
    }