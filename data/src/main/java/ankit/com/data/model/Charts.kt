package ankit.com.data.model

import ankit.com.domain.model.ChartsDomainModel

data class Charts(
    val description: String,
    val name: String,
    val period: String,
    val status: String,
    val unit: String,
    val values: List<Value>
)

fun Charts.toDomainModel(): ChartsDomainModel {
    return ChartsDomainModel(
        description = this.description,
        name = this.name,
        period = this.period,
        status = this.status,
        unit = this.unit,
        values = this.values.map { it.toDomainModel() }
    )
}