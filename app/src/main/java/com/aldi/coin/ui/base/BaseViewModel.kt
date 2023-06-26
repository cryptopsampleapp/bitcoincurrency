package com.aldi.coin.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<E>(private val uiContext: CoroutineContext) : ViewModel(),
    CoroutineScope {

    abstract fun handleEvent(event: E)

    private var jobTracker: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + jobTracker
}