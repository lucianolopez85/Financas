package com.example.financas.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.adapter.ListaTransacoesAdapter
import com.example.financas.model.Tipo
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*


class ListaTransacoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(Transacao(BigDecimal(20.50), "Alimentação",Tipo.DESPESA, Calendar.getInstance()),
        Transacao(BigDecimal(100.00), "Economia", Tipo.RECEITA, Calendar.getInstance()))

        lista_transacoes_listview.setAdapter(ListaTransacoesAdapter(transacoes, this))
    }
}