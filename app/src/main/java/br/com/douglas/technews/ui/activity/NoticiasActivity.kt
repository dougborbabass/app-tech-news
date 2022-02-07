package br.com.douglas.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentOnAttachListener
import br.com.douglas.technews.R
import br.com.douglas.technews.model.Noticia
import br.com.douglas.technews.ui.activity.extensions.transacaoFragment
import br.com.douglas.technews.ui.fragment.ListaNoticiasFragment
import br.com.douglas.technews.ui.fragment.VisualizaNoticiaFragment

private const val TITULO_APPBAR = "Notícias"

class ListaNoticiasActivity : AppCompatActivity() {

    init {
        val fm = supportFragmentManager

        val listener = FragmentOnAttachListener { _, fragment ->
            when (fragment) {
                is ListaNoticiasFragment -> {
                    configuraListaNoticias(fragment)
                }
                is VisualizaNoticiaFragment -> {
                    configuraVisualizaNoticias(fragment)
                }
            }
        }
        fm.addFragmentOnAttachListener(listener)
    }

    private fun configuraVisualizaNoticias(fragment: VisualizaNoticiaFragment) {
        fragment.quandoFinalizaTela = this::finish
        fragment.quandoSelecionaMenuEdicao = this::abreFormularioEdicao
    }

    private fun configuraListaNoticias(fragment: ListaNoticiasFragment) {
        fragment.quandoNoticiaSelecionada = this::abreVisualizadorNoticia
        fragment.quandoFABsalvaNoticiaClicado = this::abreFormularioModoCriacao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)
        title = TITULO_APPBAR

        transacaoFragment {
            add(R.id.activity_noticias_container, ListaNoticiasFragment())
        }
    }

    fun abreFormularioModoCriacao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        startActivity(intent)
    }

    fun abreVisualizadorNoticia(noticia: Noticia) {
        val fragment = VisualizaNoticiaFragment()
        val bundle = Bundle()
        bundle.putLong(NOTICIA_ID_CHAVE, noticia.id)
        fragment.arguments = bundle

        transacaoFragment {
            replace(R.id.activity_noticias_container, fragment)
        }
    }

    private fun abreFormularioEdicao(noticia: Noticia) {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticia.id)
        startActivity(intent)
    }

}
