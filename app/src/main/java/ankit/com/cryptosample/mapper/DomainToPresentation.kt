package ankit.com.cryptosample.mapper

import ankit.com.cryptosample.model.ChartsPresentationModel
import ankit.com.cryptosample.model.StatsPresentationModel
import ankit.com.cryptosample.model.Value
import ankit.com.domain.model.ChartsDomainModel
import ankit.com.domain.model.StatsDomainModel
import ankit.com.domain.model.ValueDomainModel

/**
 * Created by AnkitSingh on 3/22/21.
 */

fun StatsDomainModel.toPresentation(): StatsPresentationModel {
    return StatsPresentationModel(
        blocks_size,
        difficulty,
        estimated_btc_sent,
        estimated_transaction_volume_usd,
        hash_rate,
        market_price_usd,
        miners_revenue_btc,
        miners_revenue_usd,
        minutes_between_blocks,
        n_blocks_mined,
        n_blocks_total,
        n_btc_mined,
        n_tx,
        nextretarget,
        timestamp,
        total_btc_sent,
        total_fees_btc,
        totalbc,
        trade_volume_btc,
        trade_volume_usd
    )
}

fun ValueDomainModel.toPresentation(): Value {
    return Value(
        x,
        y
    )
}

fun ChartsDomainModel.toPresentation(): ChartsPresentationModel {
    return ChartsPresentationModel(
        description,
        name,
        period,
        status,
        unit,
        values.map { it.toPresentation() }
    )
}