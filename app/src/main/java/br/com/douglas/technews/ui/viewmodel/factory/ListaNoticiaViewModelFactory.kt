package br.com.douglas.technews.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.douglas.technews.repository.NoticiaRepository
import br.com.douglas.technews.ui.viewmodel.ListaNoticiasViewModel

class ListaNoticiaViewModelFactory(
    private val repository: NoticiaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListaNoticiasViewModel(repository) as T
    }
}