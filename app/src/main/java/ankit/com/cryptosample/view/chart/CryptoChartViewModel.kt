package ankit.com.cryptosample.view.chart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.ChartsDomainModel
import ankit.com.domain.usecase.GetChartUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by AnkitSingh on 3/23/21.
 */
class CryptoChartViewModel @ViewModelInject constructor(
    private val getChartUseCase: GetChartUseCase
) : ViewModel() {

    private val _chart = MutableLiveData<ApiResponse<ChartsDomainModel>>()
    val chart: LiveData<ApiResponse<ChartsDomainModel>> = _chart

    fun getBitcoinChart(chartName: String) {
        viewModelScope.launch {
            getChartUseCase(chartName)
                .collect {
                    _chart.value = it
                }
        }
    }
}