package br.com.douglas.technews.retrofity

import br.com.douglas.technews.retrofity.service.NoticiaService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://bc1b-2804-14d-4c89-89c4-e10e-f57-e1ec-7067.ngrok.io"

class AppRetrofit {

    private val client by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val noticiaService: NoticiaService by lazy {
        retrofit.create(NoticiaService::class.java)
    }

}