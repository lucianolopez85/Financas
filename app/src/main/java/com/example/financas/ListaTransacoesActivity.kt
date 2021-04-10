package com.example.financas.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.ResumoView
import com.example.financas.adapter.ListaTransacoesAdapter
import com.example.financas.extension.moedaFormatBR
import com.example.financas.model.Tipo
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal
import java.util.*


class ListaTransacoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = exemploTransacao()

        configuraLista(transacoes)
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun exemploTransacao() = listOf(
            Transacao(
                    valor = BigDecimal(320.50),
                    categoria = "SSD",
                    tipo = Tipo.DESPESA,
                    data = Calendar.getInstance()),
            Transacao(
                    valor = BigDecimal(100.00),
                    categoria = "Economia",
                    tipo = Tipo.RECEITA,
                    data = Calendar.getInstance()),
            Transacao(
                    valor = BigDecimal(50.50),
                    tipo = Tipo.DESPESA,
                    data = Calendar.getInstance()),
            Transacao(
                    valor = BigDecimal(1800.50),
                    tipo = Tipo.RECEITA,
                    data = Calendar.getInstance()))
}