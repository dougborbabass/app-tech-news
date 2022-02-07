package br.com.douglas.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.technews.R
import br.com.douglas.technews.model.Noticia
import br.com.douglas.technews.ui.fragment.ListaNoticiaFragment

private const val TITULO_APPBAR = "Notícias"
private const val MENSAGEM_FALHA_CARREGAR_NOTICIAS = "Não foi possível carregar as novas notícias"

@Suppress("DEPRECATION")
class ListaNoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_noticias)
        title = TITULO_APPBAR
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is ListaNoticiaFragment) {
            fragment.quandoNoticiaSelecionada = {
                abreVisualizadorNoticia(it)
            }
            fragment.quandoFABsalvaNoticiaClicado = {
                abreFormularioModoCriacao()
            }
        }
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
