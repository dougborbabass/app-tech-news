package br.com.douglas.technews.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.douglas.technews.repository.NoticiaRepository
import br.com.douglas.technews.ui.viewmodel.FormularioNoticiaViewModel


class FormularioNoticiaViewModelFactory(
    private val repository: NoticiaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FormularioNoticiaViewModel(repository) as T
    }
}
