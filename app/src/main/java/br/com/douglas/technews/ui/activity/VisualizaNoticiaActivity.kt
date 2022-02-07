package br.com.douglas.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentOnAttachListener
import br.com.douglas.technews.R
import br.com.douglas.technews.ui.fragment.VisualizaNoticiaFragment

private const val TITULO_APPBAR = "Notícia"


class VisualizaNoticiaActivity : AppCompatActivity() {

    private val noticiaId: Long by lazy {
        intent.getLongExtra(NOTICIA_ID_CHAVE, 0)
    }

    init {
        val fm = supportFragmentManager

        val listener = FragmentOnAttachListener { _, fragment ->
            if (fragment is VisualizaNoticiaFragment) {
                fragment.quandoFinalizaTela = { finish() }
                fragment.quandoSelecionaMenuEdicao = { abreFormularioEdicao() }
            }
        }
        fm.addFragmentOnAttachListener(listener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualiza_noticia)
        title = TITULO_APPBAR
    }

    private fun abreFormularioEdicao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticiaId)
        startActivity(intent)
    }

}