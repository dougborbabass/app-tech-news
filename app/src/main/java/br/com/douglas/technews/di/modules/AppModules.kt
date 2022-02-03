package br.com.douglas.technews.di.modules

import androidx.room.Room
import br.com.douglas.technews.database.AppDatabase
import br.com.douglas.technews.database.dao.NoticiaDAO
import br.com.douglas.technews.repository.NoticiaRepository
import br.com.douglas.technews.retrofity.webclient.NoticiaWebClient
import br.com.douglas.technews.ui.viewmodel.FormularioNoticiaViewModel
import br.com.douglas.technews.ui.viewmodel.ListaNoticiasViewModel
import br.com.douglas.technews.ui.viewmodel.VisualizaNoticiaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "news.db"

/**
 * appModules define os módulos que serão injetados
 *
 * single<AppDatabase> faz a configuração para injeção do AppDatabase
 * single<NoticiaDAO> faz uso do database para acessar o dao
 * single<NoticiaWebClient> faz a instancia do webclient
 * single<NoticiaRepository> faz uso do dao e webclient já injetados
 *
 */

val appModules = module {
    // singleton mode
    single<AppDatabase> {
        Room.databaseBuilder(
            get(), //busca um contexto através do koin
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }

    single<NoticiaDAO> {
        get<AppDatabase>().noticiaDAO
    }

    single<NoticiaWebClient> {
        NoticiaWebClient()
    }

    single<NoticiaRepository> {
        NoticiaRepository(get(), get())
    }

    viewModel<ListaNoticiasViewModel> {
        ListaNoticiasViewModel(get())
    }

    viewModel<FormularioNoticiaViewModel> {
        FormularioNoticiaViewModel(get())
    }

    viewModel<VisualizaNoticiaViewModel> { (id: Long) ->
        VisualizaNoticiaViewModel(id, get())
    }
}