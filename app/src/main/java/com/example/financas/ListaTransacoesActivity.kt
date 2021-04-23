package com.example.financas.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.ResumoView
import com.example.financas.adapter.ListaTransacoesAdapter
import com.example.financas.delegate.TransacaoDelegate
import com.example.financas.dialog.AdicionaTransacaoDialog
import com.example.financas.model.Tipo
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    // mutableList é possível usar uma lista que permite modificar seus valores internos, litOf não permite alteração
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita
                .setOnClickListener {
                    chamaDialogAdicao(Tipo.RECEITA)
                }
        lista_transacoes_adiciona_despesa
                .setOnClickListener {
                    chamaDialogAdicao(Tipo.DESPESA)
                }
    }

    private fun chamaDialogAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                .configDialog(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
        //necessário atualizar novamente após adicionar valor a lista
    }

    private fun configuraResumo() {
        val view = window.decorView
        //O decor é a view "pai de todos", busca qualquer view a partir do decorView.
        // ex: LinearLayout, RelativeLayout, ConstraintLayout
        val resumoView = ResumoView(view, transacoes, this)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }


}