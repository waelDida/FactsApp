package com.wapp.factsapp.resource

sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    class SignSuccess<out T>: Resource<T>()
    data class Empty<out T>(val data: T): Resource<T>()
    class Failure<out T>():Resource<T>()
    class Null<out T>:Resource<T>()
}