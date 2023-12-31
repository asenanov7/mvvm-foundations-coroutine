package com.example.foundation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*


/**
 * Transform this Flow into Hot Shared Flow (like by using [shareIn] operator), but which is finite and
 * which also propagates exceptions from the source flow.
 */
fun <T> Flow<T>.finiteShareIn(
    coroutineScope: CoroutineScope,
    started: SharingStarted = SharingStarted.Eagerly ,
    replay: Int = 1 ): Flow<T> {
    return this
        .map<T, Element<T>> { item -> ItemElement(item) }
        .onCompletion { emit(CompletedElement()) }
        .catch { exception -> emit(ErrorElement(exception)) }
        .shareIn(coroutineScope, started, replay)
        .map {
            if (it is ErrorElement) throw it.error
            else return@map it
        }
        .takeWhile { it is ItemElement }
        .map { (it as ItemElement).item }
}

// --- helper classes for materializing source flows

private sealed class Element<T>

private class ItemElement<T>(
    val item: T,
) : Element<T>()

private class ErrorElement<T>(
    val error: Throwable,
) : Element<T>()

private class CompletedElement<T> : Element<T>()
