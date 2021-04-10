package com.example.financas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.example.financas.R
import com.example.financas.extension.dataFormatBR
import com.example.financas.extension.limiteStringCategoria
import com.example.financas.extension.moedaFormatBR
import com.example.financas.model.Tipo
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
        private val transacoes: List<Transacao>,
        private val context: Context): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCreated = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]

        if (transacao.tipo == Tipo.RECEITA){
            viewCreated.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
            viewCreated.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        }else{
            viewCreated.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
            viewCreated.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }
        viewCreated.transacao_valor.text = transacao.valor.moedaFormatBR()
        viewCreated.transacao_categoria.text = transacao.categoria.limiteStringCategoria()
        viewCreated.transacao_data.text = transacao.data.dataFormatBR()
        return viewCreated
    }
    override fun getCount(): Int {
        return transacoes.size
    }
    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
}