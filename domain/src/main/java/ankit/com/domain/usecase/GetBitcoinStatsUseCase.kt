package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.StatsDomainModel
import ankit.com.domain.repository.BlockChainStatsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 3/22/21.
 */
class GetBitcoinStatsUseCase @Inject constructor(
    private val repository: BlockChainStatsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseUseCase<String?, StatsDomainModel>(ioDispatcher) {

    override fun execute(parameters: String?): Flow<ApiResponse<StatsDomainModel>> {
        return repository.getBitcoinStats()
    }
}