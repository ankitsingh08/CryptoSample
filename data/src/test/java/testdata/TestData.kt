package testdata

import ankit.com.data.model.Charts
import ankit.com.data.model.Stats
import ankit.com.data.model.Value
import ankit.com.domain.model.ChartsDomainModel
import ankit.com.domain.model.StatsDomainModel
import ankit.com.domain.model.ValueDomainModel

/**
 * Created by AnkitSingh on 3/28/21.
 */
object TestData {
    internal fun getBitcoinStats(): Stats {
        return Stats(
            209271483,
            21865558044610,
            8111221858467,
            4.523220436007564E9,
            1.7608473133198056E11,
            55764.97,
            1084,
            6.046105101207468E7,
            8.1429,
            162,
            676584,
            101250000000,
            298602,
            677375,
            1.616875889E12,
            120209627954287,
            7171202436,
            1866615000000000,
            8247.28,
            4.5990932178160006E8
        )
    }

    internal fun getBitcoinChartsData(): Charts {
        val value1 = Value(1585353600, 6369.09)
        val value2 = Value(1585440000, 6260.95)
        val value3 = Value(1585526400, 5885.41)
        val value4 = Value(1585353600, 6405.29)
        val valueList = mutableListOf<Value>()
        valueList.add(value1)
        valueList.add(value2)
        valueList.add(value3)
        valueList.add(value4)

        return Charts(
            "Average USD market price across major bitcoin exchanges",
            "Market Price (USD)",
            "day",
            "ok",
            "USD",
            valueList
        )
    }
}