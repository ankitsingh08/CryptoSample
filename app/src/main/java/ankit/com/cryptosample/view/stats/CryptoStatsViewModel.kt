package ankit.com.cryptosample.view.stats

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.StatsDomainModel
import ankit.com.domain.usecase.GetBitcoinStatsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by AnkitSingh on 3/22/21.
 */
private const val EMPTY_STRING = ""
class CryptoStatsViewModel @ViewModelInject constructor(
    private val getBitcoinStatsUseCase: GetBitcoinStatsUseCase
) : ViewModel() {

    private val _stats = MutableLiveData<ApiResponse<StatsDomainModel>>()
    val stats: LiveData<ApiResponse<StatsDomainModel>> = _stats

    fun getBitcoinStats() {
        viewModelScope.launch {
            getBitcoinStatsUseCase(EMPTY_STRING)
                .collect {
                    _stats.value = it
                }
        }
    }
}