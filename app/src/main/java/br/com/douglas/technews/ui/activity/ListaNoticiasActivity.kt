package br.com.douglas.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentOnAttachListener
import br.com.douglas.technews.R
import br.com.douglas.technews.model.Noticia
import br.com.douglas.technews.ui.fragment.ListaNoticiaFragment

private const val TITULO_APPBAR = "Notícias"

class ListaNoticiasActivity : AppCompatActivity() {

    init {
        val fm = supportFragmentManager

        val listener = FragmentOnAttachListener { _, fragment ->
            if (fragment is ListaNoticiaFragment) {
                fragment.quandoNoticiaSelecionada = {
                    abreVisualizadorNoticia(it)
                }
                fragment.quandoFABsalvaNoticiaClicado = {
                    abreFormularioModoCriacao()
                }
            }
        }
        fm.addFragmentOnAttachListener(listener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_noticias)
        title = TITULO_APPBAR
    }

    fun abreFormularioModoCriacao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        startActivity(intent)
    }

    fun abreVisualizadorNoticia(it: Noticia) {
        val intent = Intent(this, VisualizaNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, it.id)
        startActivity(intent)
    }

}
