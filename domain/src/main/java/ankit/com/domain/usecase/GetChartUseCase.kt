package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.ChartsDomainModel
import ankit.com.domain.repository.BlockChainChartsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 3/23/21.
 */
class GetChartUseCase @Inject constructor(
    private val repository: BlockChainChartsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseUseCase<String, ChartsDomainModel>(ioDispatcher) {

    override fun execute(parameters: String): Flow<ApiResponse<ChartsDomainModel>> {
        return repository.getChartInformation(parameters)
    }
}