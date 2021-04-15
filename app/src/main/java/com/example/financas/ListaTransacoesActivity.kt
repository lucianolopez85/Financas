package com.example.financas.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.ResumoView
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
        val transacoes: List<Transacao> = exemploTransacao()
        configuraResumo(transacoes)
        configuraLista(transacoes)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        //O decor é a view "pai de todos", busca qualquer view a partir do decorView.
        // ex: LinearLayout, RelativeLayout, ConstraintLayout
        val resumoView = ResumoView(view, transacoes, this)
        with(resumoView){
            adicionaDespesa()
            adicionaReceita()
            adicionaTotal()
        }
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
                    valor = BigDecimal(150.00),
                    categoria = "Economia",
                    tipo = Tipo.RECEITA,
                    data = Calendar.getInstance()),
            Transacao(
                    valor = BigDecimal(350.50),
                    tipo = Tipo.DESPESA,
                    data = Calendar.getInstance()),
            Transacao(
                    valor = BigDecimal(1500.55),
                    tipo = Tipo.DESPESA,
                    categoria = "Aluguel",
                    data = Calendar.getInstance()),
            Transacao(
                    valor = BigDecimal(2100.10),
                    tipo = Tipo.RECEITA,
                    categoria = "Salário",
                    data = Calendar.getInstance()))
}