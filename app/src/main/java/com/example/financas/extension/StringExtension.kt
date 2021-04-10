package com.example.financas.extension

fun String.limiteStringCategoria(): String{

    if (this.length> 13){
        return "${this.substring(0,13)}..."
    }
    return this
}