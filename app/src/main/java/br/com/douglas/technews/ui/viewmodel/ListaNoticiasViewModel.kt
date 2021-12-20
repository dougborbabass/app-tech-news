package br.com.douglas.technews.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.douglas.technews.model.Noticia
import br.com.douglas.technews.repository.NoticiaRepository

class ListaNoticiasViewModel(
    private val repository: NoticiaRepository
) : ViewModel() {

    fun buscaTodos(
        quandoSucesso: (noticiasNovas: List<Noticia>) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        repository.buscaTodos(quandoSucesso, quandoFalha)
    }
}