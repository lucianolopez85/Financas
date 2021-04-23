package com.example.financas.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limiteStringCategoria(): String{

    if (this.length> 13){
        return "${this.substring(0,13)}..."
    }
    return this
}

fun String.converteParaCalendar(): Calendar{
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida = simpleDateFormat.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}