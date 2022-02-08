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


class ListaNoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        if (savedInstanceState == null) {
            transacaoFragment {
                add(R.id.activity_noticias_container_primario, ListaNoticiasFragment())
            }
        }
    }

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
            addToBackStack(null)
            replace(R.id.activity_noticias_container_primario, fragment)
        }
    }

    private fun abreFormularioEdicao(noticia: Noticia) {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticia.id)
        startActivity(intent)
    }

}
