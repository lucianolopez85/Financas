package com.example.financas.extension

import java.text.SimpleDateFormat
import java.util.Calendar

fun Calendar.dataFormatBR(): String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val simpleDateFormat = SimpleDateFormat(formatoBrasileiro)

    return simpleDateFormat.format(this.time)
}