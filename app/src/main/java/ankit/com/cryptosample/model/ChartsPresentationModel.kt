package ankit.com.cryptosample.model

data class ChartsPresentationModel(
    val description: String,
    val name: String,
    val period: String,
    val status: String,
    val unit: String,
    val values: List<Value>
)