package ankit.com.data.repository

import ankit.com.data.model.toDomainModel
import ankit.com.data.remote.BlockChainService
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.ChartsDomainModel
import ankit.com.domain.repository.BlockChainChartsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 3/23/21.
 */
class BlockChainChartRepositoryImpl  @Inject constructor(private val blockChainService: BlockChainService)
    : BlockChainChartsRepository {

    override fun getChartInformation(name : String): Flow<ApiResponse<ChartsDomainModel>> {
        return flow {
            try {
                val response = blockChainService.getBitcoinChart(name).toDomainModel()
                emit(ApiResponse.Success(response))

            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }
}