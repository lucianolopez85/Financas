package com.example.financas

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.financas.extension.moedaFormatBR
import com.example.financas.model.Resumo
import com.example.financas.model.Tipo
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(context: Context,
                 private val view: View,
                 private val transacoes: List<Transacao>) {

    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)

    fun atualiza(){
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val totalReceita = Resumo(transacoes).receita
            with(view.resumo_card_receita){
                text = totalReceita.moedaFormatBR()
                setTextColor(corReceita)
        }
    }

    private fun adicionaDespesa() {
        var totalDespesa = Resumo(transacoes).despesa
            with(view.resumo_card_despesa){
                text = totalDespesa.moedaFormatBR()
                setTextColor(corDespesa)
        }
    }

    private fun adicionaTotal(){
        val total = Resumo(transacoes).total
        val cor = qualCor(total)
            with(view.resumo_card_total){
                setTextColor(cor)
                text = total.moedaFormatBR()
        }
    }

    private fun qualCor(valor: BigDecimal): Int {
        if (valor <= BigDecimal.ZERO){
            //As entidades do Kotlin são objeto
            //não é necessário usar compareTo e equals, nos operadores já esta implícito
            return corDespesa
        } else {
            return corReceita
        }
    }
}