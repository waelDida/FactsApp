package com.wapp.factsapp.resource

sealed class SignState {
    class RegisteredWithSuccess: SignState()
    class failedToSignIn: SignState()
}