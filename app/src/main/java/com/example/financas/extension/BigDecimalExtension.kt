package com.example.financas.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.moedaFormatBR(): String {
    val formatoBrasileiro = DecimalFormat
            .getCurrencyInstance(Locale("pt", "br"))

    return formatoBrasileiro.format(this).replace("R$", "R$ ")
}

