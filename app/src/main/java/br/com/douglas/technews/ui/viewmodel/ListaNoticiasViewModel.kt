package br.com.douglas.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.douglas.technews.model.Noticia
import br.com.douglas.technews.repository.NoticiaRepository
import br.com.douglas.technews.repository.Resource

class ListaNoticiasViewModel(
    private val repository: NoticiaRepository
) : ViewModel() {

    fun buscaTodos(): LiveData<Resource<List<Noticia>?>> {
        return repository.buscaTodos()
    }
}