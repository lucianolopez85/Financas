package com.example.financas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.financas.R
import com.example.financas.extension.formataParaBrasileiro
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesAdapter(transacoes: List<Transacao>, context: Context): BaseAdapter() {
    private val transacoes = transacoes
    private val context = context

    override fun getCount(): Int {
        return transacoes.size
    }
    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCreated = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]

        viewCreated.transacao_valor.text = transacao.valor.toString()
        viewCreated.transacao_categoria.text = transacao.categoria
        viewCreated.transacao_data.text = transacao.data.formataParaBrasileiro()

        return viewCreated
    }


}