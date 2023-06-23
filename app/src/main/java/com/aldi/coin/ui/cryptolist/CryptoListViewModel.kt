package com.aldi.coin.ui.cryptolist

import android.util.Log
import androidx.annotation.DrawableRes
import com.aldi.coin.Constants.ONE_MINUTE
import com.aldi.coin.data.model.Crypto
import com.aldi.coin.data.repository.ApiResponse
import com.aldi.coin.data.repository.CoinCapRepoImpl
import com.aldi.coin.data.repository.safeApiCall
import com.aldi.coin.di.DefaultDispatcher
import com.aldi.coin.domain.interactor.MapIconUseCase
import com.aldi.coin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CryptoListEvent {
    object OnRefresh : CryptoListEvent()
}

sealed class CryptoListUiState {
    object InProgress : CryptoListUiState()
    data class Success(val cryptoList: List<Crypto>, @DrawableRes val iconList: List<Int>) :
        CryptoListUiState()

    data class Error(val message: String) : CryptoListUiState()
}


@HiltViewModel
class CryptoListViewModel @Inject constructor(
    @DefaultDispatcher uiContext: CoroutineDispatcher,
    private val coinRepo: CoinCapRepoImpl
) : BaseViewModel<CryptoListEvent>(uiContext) {


    private val _uiState = MutableStateFlow<CryptoListUiState>(CryptoListUiState.InProgress)
    val uiState: StateFlow<CryptoListUiState> = _uiState.asStateFlow()

    init {
        getTopCryptosContinuously()
    }

    override fun handleEvent(event: CryptoListEvent) {
        when (event) {
            CryptoListEvent.OnRefresh -> launch { getCryptoList() }
        }
    }

    private fun getTopCryptosContinuously() = launch {
        while (isActive) {
            getCryptoList()
            delay(ONE_MINUTE)
        }
    }

    private suspend fun getCryptoList() {
        safeApiCall(coroutineContext) { coinRepo.getCryptoList(10) }.collect { state ->
            when (state) {
                ApiResponse.Loading -> _uiState.value = CryptoListUiState.InProgress
                is ApiResponse.Success -> _uiState.value =
                    state.data?.let {
                        CryptoListUiState.Success(
                            it,
                            it.map { crypto: Crypto -> MapIconUseCase(crypto) })
                    }
                        ?: CryptoListUiState.Error(NullPointerException::class.java.simpleName)

                is ApiResponse.Error -> {
                    _uiState.value =
                        CryptoListUiState.Error(state.localizedMessage)
                    Log.d(this::class.simpleName, state.localizedMessage)
                }
            }
        }
    }
}