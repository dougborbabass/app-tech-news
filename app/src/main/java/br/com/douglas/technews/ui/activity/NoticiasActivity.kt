package br.com.douglas.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentOnAttachListener
import androidx.fragment.app.FragmentTransaction
import br.com.douglas.technews.R
import br.com.douglas.technews.model.Noticia
import br.com.douglas.technews.ui.activity.extensions.transacaoFragment
import br.com.douglas.technews.ui.fragment.ListaNoticiasFragment
import br.com.douglas.technews.ui.fragment.VisualizaNoticiaFragment
import kotlinx.android.synthetic.main.activity_noticias.*

private const val TAG_FRAGMENT_VISUALIZA_NOTICIA = "visualizaNoticia"

class ListaNoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)
        configuraFragmentPeloEstadoAtual(savedInstanceState)
    }

    private fun configuraFragmentPeloEstadoAtual(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            abreListaNoticias()
        } else {
            tentaReabrirFragmentVisualizaNoticia()
        }
    }

    private fun abreListaNoticias() {
        transacaoFragment {
            add(R.id.activity_noticias_container_primario, ListaNoticiasFragment())
        }
    }

    private fun tentaReabrirFragmentVisualizaNoticia() {
        supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_VISUALIZA_NOTICIA)
            ?.let { fragment ->

                val argumentos = fragment.arguments
                val novoFragment = VisualizaNoticiaFragment()
                novoFragment.arguments = argumentos

                removeFragmentVisualizaNoticia(fragment)

                transacaoFragment {
                    val container = configuraContainerFragmentVisualizaNoticia()
                    replace(container, novoFragment, TAG_FRAGMENT_VISUALIZA_NOTICIA)
                }
            }
    }

    private fun FragmentTransaction.configuraContainerFragmentVisualizaNoticia() =
        if (activity_noticias_container_secundario != null) {
            R.id.activity_noticias_container_secundario
        } else {
            addToBackStack(null)
            R.id.activity_noticias_container_primario
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
        fragment.quandoFinalizaTela = {
            supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_VISUALIZA_NOTICIA)
                ?.let { fragment ->
                    removeFragmentVisualizaNoticia(fragment)
                }
        }
        fragment.quandoSelecionaMenuEdicao = this::abreFormularioEdicao
    }

    private fun removeFragmentVisualizaNoticia(fragment: Fragment) {
        transacaoFragment {
            remove(fragment)
        }
        supportFragmentManager.popBackStack()
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
            val container = configuraContainerFragmentVisualizaNoticia()
            replace(container, fragment, TAG_FRAGMENT_VISUALIZA_NOTICIA)
        }
    }

    private fun abreFormularioEdicao(noticia: Noticia) {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticia.id)
        startActivity(intent)
    }

}
