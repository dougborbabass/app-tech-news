package br.com.douglas.technews.repository

class Resource<T>(
    val dado: T?,
    val erro: String? = null
)