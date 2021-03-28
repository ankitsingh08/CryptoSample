package ankit.com.domain.model

data class ChartsDomainModel(
    val description: String,
    val name: String,
    val period: String,
    val status: String,
    val unit: String,
    val values: List<ValueDomainModel>
)