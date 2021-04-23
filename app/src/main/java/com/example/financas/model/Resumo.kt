package com.example.financas.model

import java.math.BigDecimal

class Resumo (private val transacoes: List<Transacao>){

    val receita get() = somaAqui(Tipo.RECEITA)

    val despesa get() = somaAqui(Tipo.DESPESA)

    val total get() = receita.subtract(despesa)

    private fun somaAqui(tipo: Tipo):BigDecimal{
        val somaTransacaoPeloTipo = transacoes
            .filter { transacao -> transacao.tipo == tipo }
            .sumByDouble { transacao -> transacao.valor.toDouble() }
        return BigDecimal(somaTransacaoPeloTipo)
    }


}