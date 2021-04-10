package com.example.financas

import android.view.View
import com.example.financas.extension.moedaFormatBR
import com.example.financas.model.Tipo
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val view: View,
                 private val transacoes: List<Transacao> ) {

     fun adicionaReceita() {
        var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }
        view.resumo_card_receita.text = totalReceita.moedaFormatBR()
    }

    fun adicionaDespesa() {
        var totalDespesa = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }
        view.resumo_card_despesa.text = totalDespesa.moedaFormatBR()
    }
}