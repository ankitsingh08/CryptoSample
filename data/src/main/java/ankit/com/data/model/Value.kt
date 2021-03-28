package ankit.com.data.model

import ankit.com.domain.model.ValueDomainModel

data class Value(
    val x: Int,
    val y: Double
)

fun Value.toDomainModel(): ValueDomainModel {
    return ValueDomainModel(
        x= this.x,
        y = this.y
    )
}