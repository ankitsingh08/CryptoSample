package ankit.com.domain.model

data class StatsDomainModel(
    val blocks_size: Int,
    val difficulty: Long,
    val estimated_btc_sent: Long,
    val estimated_transaction_volume_usd: Double,
    val hash_rate: Double,
    val market_price_usd: Double,
    val miners_revenue_btc: Int,
    val miners_revenue_usd: Double,
    val minutes_between_blocks: Double,
    val n_blocks_mined: Int,
    val n_blocks_total: Int,
    val n_btc_mined: Long,
    val n_tx: Int,
    val nextretarget: Int,
    val timestamp: Double,
    val total_btc_sent: Long,
    val total_fees_btc: Long,
    val totalbc: Long,
    val trade_volume_btc: Double,
    val trade_volume_usd: Double
)