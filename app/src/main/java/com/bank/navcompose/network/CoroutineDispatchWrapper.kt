package com.bank.navcompose.network

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchWrapper {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}