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

class ResumoView(private val view: View,
                 private val transacoes: List<Transacao>,
                 context: Context) {
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)

    fun adicionaReceita() {
        val totalReceita = Resumo(transacoes).receita()
        with(view.resumo_card_receita){           //chama o objeto apenas uma vez, coloca suas propriedades e suas funções
            text = totalReceita.moedaFormatBR()
            view.resumo_card_receita.setTextColor(corReceita)
        }
    }

    fun adicionaDespesa() {
        var totalDespesa = Resumo(transacoes).despesa()
        with(view.resumo_card_despesa){
            text = totalDespesa.moedaFormatBR()
            view.resumo_card_despesa.setTextColor(corDespesa)
        }
    }

    fun adicionaTotal(){
        val total = Resumo(transacoes).total()
        val cor = qualCor(total)
        with(view.resumo_card_total){
            setTextColor(cor)
            text = total.moedaFormatBR()
        }
    }

    private fun qualCor(valor: BigDecimal): Int {
        if (valor >= BigDecimal.ZERO){ // No kotlin trabalha com objeto, então o compareTo já esta implicito
            return corDespesa
        } else {
            return corReceita
        }
    }
}