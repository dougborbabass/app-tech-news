package br.com.douglas.technews.ui.activity.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.mostraErro(mensagem: String) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}