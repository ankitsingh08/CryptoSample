package ankit.com.data.model

import ankit.com.domain.model.StatsDomainModel

data class Stats(
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

fun Stats.toDomainModel(): StatsDomainModel {
    return StatsDomainModel(
        blocks_size = this.blocks_size,
        difficulty = this.difficulty,
        estimated_btc_sent = this.estimated_btc_sent,
        estimated_transaction_volume_usd = this.estimated_transaction_volume_usd,
        hash_rate = this.hash_rate,
        market_price_usd = this.market_price_usd,
        miners_revenue_btc = this.miners_revenue_btc,
        miners_revenue_usd = this.miners_revenue_usd,
        minutes_between_blocks = this.minutes_between_blocks,
        n_blocks_mined = this.n_blocks_mined,
        n_blocks_total = this.n_blocks_total,
        n_btc_mined = this.n_btc_mined,
        n_tx = this.n_tx,
        nextretarget = this.nextretarget,
        timestamp = this.timestamp,
        total_btc_sent = this.total_btc_sent,
        total_fees_btc = this.total_fees_btc,
        totalbc = this.totalbc,
        trade_volume_btc = this.trade_volume_btc,
        trade_volume_usd = this.trade_volume_usd
    )
}