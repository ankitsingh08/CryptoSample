package ankit.com.data.repository

import ankit.com.data.model.toDomainModel
import ankit.com.data.remote.BlockChainService
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.StatsDomainModel
import ankit.com.domain.repository.BlockChainStatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 3/22/21.
 */
class BlockChainStatsRepositoryImpl @Inject constructor(private val blockChainService: BlockChainService)
    : BlockChainStatsRepository {

    override fun getBitcoinStats(): Flow<ApiResponse<StatsDomainModel>> {
        return flow {
            try {
                val response = blockChainService.getBitcoinStats().toDomainModel()
                emit(ApiResponse.Success(response))

            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }
}