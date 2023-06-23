package com.aldi.coin.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<E>(protected val uiContext: CoroutineContext) : ViewModel(),
    CoroutineScope {

    abstract fun handleEvent(event: E)

    private var jobTracker: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + jobTracker

//    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        _baseUiState.value = UiState.Error(throwable.localizedMessage?:throwable.toString())
//    }
}