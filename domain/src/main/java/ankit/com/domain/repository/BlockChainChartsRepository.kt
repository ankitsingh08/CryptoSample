package ankit.com.domain.repository

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.ChartsDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by AnkitSingh on 3/23/21.
 */
interface BlockChainChartsRepository {
    fun getChartInformation(name: String): Flow<ApiResponse<ChartsDomainModel>>
}