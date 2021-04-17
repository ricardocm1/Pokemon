package com.android.pokemon.extensions

fun <T> List<T>?.handleOptional() = this ?: emptyList()