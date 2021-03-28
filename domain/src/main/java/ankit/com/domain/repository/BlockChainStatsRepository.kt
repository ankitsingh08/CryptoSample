package ankit.com.domain.repository

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.StatsDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by AnkitSingh on 3/22/21.
 */
interface BlockChainStatsRepository {
    fun getBitcoinStats(): Flow<ApiResponse<StatsDomainModel>>
}