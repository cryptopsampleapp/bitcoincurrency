package com.aldi.coin.ui.cryptodetail

import androidx.annotation.DrawableRes
import androidx.lifecycle.SavedStateHandle
import com.aldi.coin.Constants.CRYPTO_DETAIL_ARG
import com.aldi.coin.data.model.Crypto
import com.aldi.coin.data.repository.ApiResponse
import com.aldi.coin.data.repository.CoinCapRepoImpl
import com.aldi.coin.data.repository.safeApiCall
import com.aldi.coin.di.DefaultDispatcher
import com.aldi.coin.domain.interactor.MapIconUseCase
import com.aldi.coin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CryptoDetailEvent {
    object OnRefresh : CryptoDetailEvent()
}


sealed class CryptoDetailUiState {
    object InProgress : CryptoDetailUiState()
    data class Success(val crypto: Crypto) : CryptoDetailUiState()
    data class Error(val message: String) : CryptoDetailUiState()
}

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @DefaultDispatcher uiContext: CoroutineDispatcher,
    private val coinRepo: CoinCapRepoImpl
) : BaseViewModel<CryptoDetailEvent>(uiContext) {
    private val cryptoId: String = checkNotNull(savedStateHandle[CRYPTO_DETAIL_ARG])

    init {
        getCryptoById(cryptoId = cryptoId)
    }

    private val _uiState = MutableStateFlow<CryptoDetailUiState>(CryptoDetailUiState.InProgress)
    val uiState: StateFlow<CryptoDetailUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    override fun handleEvent(event: CryptoDetailEvent) {
        when (event) {
            CryptoDetailEvent.OnRefresh -> getCryptoById(cryptoId)
        }
    }

    private fun getCryptoById(cryptoId: String) = launch {
        safeApiCall(coroutineContext) { coinRepo.getCryptoById(cryptoId) }.collect { state ->
            when (state) {
                ApiResponse.Loading -> _uiState.value = CryptoDetailUiState.InProgress
                is ApiResponse.Success -> _uiState.value =
                    state.data?.let { CryptoDetailUiState.Success(it) }
                        ?: CryptoDetailUiState.Error(NullPointerException::class.java.simpleName)

                is ApiResponse.Error -> _uiState.value =
                    CryptoDetailUiState.Error(state.localizedMessage)
            }
        }
        _isRefreshing.emit(false)
    }
}