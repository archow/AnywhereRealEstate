package com.android.anywhererealestate.model

import java.lang.Exception

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(val exception: Exception): ResultOf<Nothing>()
}
