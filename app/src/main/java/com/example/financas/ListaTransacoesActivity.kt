package com.example.financas.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.ResumoView
import com.example.financas.adapter.ListaTransacoesAdapter
import com.example.financas.dao.TransacaoDAO
import com.example.financas.delegate.TransacaoDelegate
import com.example.financas.dialog.AdicionaTransacaoDialog
import com.example.financas.dialog.AlteraTransacaoDialog
import com.example.financas.model.Tipo
import com.example.financas.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes
    private val viewDaActivity by lazy {
        window.decorView
    }
    private val viewGroupDaActivity by lazy{
        viewDaActivity as ViewGroup
    }

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
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
                .configDialog(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        adiciona(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
        //necessário atualizar novamente após adicionar valor a lista
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this,viewDaActivity,transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview){
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, posicao, _ ->
                val transacao = transacoes[posicao]
                chamaDialogAlteracao(transacao, posicao)
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val idDoMenu = item?.itemId
        if (idDoMenu == 1){
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val positionDaTransacao = adapterMenuInfo.position
            remove(positionDaTransacao)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(positionDaTransacao: Int) {
        dao.remove(positionDaTransacao)
        atualizaTransacoes()
    }

    private fun chamaDialogAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
                .configDialog(transacao, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        altera(transacao, posicao)
                    }
                })
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        dao.altera(transacao, posicao)
        atualizaTransacoes()
    }
}