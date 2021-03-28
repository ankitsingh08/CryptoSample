package ankit.com.cryptosample.view.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ankit.com.cryptosample.databinding.CryptoChartLayoutBinding
import ankit.com.cryptosample.mapper.toPresentation
import ankit.com.cryptosample.model.ChartsPresentationModel
import ankit.com.cryptosample.view.stats.CHART_NAME
import ankit.com.domain.core.ApiResponse
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.crypto_chart_layout.*
import kotlinx.android.synthetic.main.crypto_stats_layout.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private const val ANIMATION_DURATION = 1000
@AndroidEntryPoint
class CryptoChartFragment: Fragment() {
    private val cryptoChartViewModel: CryptoChartViewModel by viewModels()

    private lateinit var binding: CryptoChartLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CryptoChartLayoutBinding.inflate(inflater, container, false)
        initializeUI()
        return binding.root
    }

    private fun initializeUI() {
        val chartName = arguments?.getString(CHART_NAME) ?: ""
        cryptoChartViewModel.getBitcoinChart(chartName)
        cryptoChartViewModel.chart.observe(viewLifecycleOwner, Observer { apiResponse->
            apiResponse?.let {
                when (apiResponse) {
                    is ApiResponse.Loading -> chart_progress_bar.visibility = View.VISIBLE
                    is ApiResponse.Success -> {
                        renderGraph(apiResponse.data.toPresentation())
                    }
                    is ApiResponse.Error -> {
                        chart_progress_bar.visibility = View.GONE
                        tv_error_view.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun renderGraph(chartsPresentationModel: ChartsPresentationModel?) {
        chartsPresentationModel?.let {
            val entryList =
                chartsPresentationModel.values.map { Entry(it.x.toFloat(), it.y.toFloat()) }
            renderGraph(entryList, chartsPresentationModel.name,
                chartsPresentationModel.description
            )
        }
    }

    //Graph is rendered using https://github.com/PhilJay/MPAndroidChart library
    private fun renderGraph(entries: List<Entry>? , name: String, descriptionText: String) {
        val lineDataSet = LineDataSet(entries, name)
        binding.chartTitle.text = descriptionText
        val lineData = LineData(lineDataSet)
        binding.graphView.data = lineData
        binding.graphView.apply {
            data = lineData
            xAxis.apply {
                isEnabled = true
                position = XAxis.XAxisPosition.TOP_INSIDE
                textSize = 10f
                setDrawAxisLine(false)
                setDrawGridLines(true)
                textColor = Color.BLACK
                setCenterAxisLabels(true)
                valueFormatter = object: ValueFormatter() {
                    private val mFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)

                    override fun getFormattedValue(value: Float): String {
                        val millis = TimeUnit.DAYS.toMillis(value.toLong())
                        return mFormat.format(Date(millis))
                    }
                }
            }
            animateX(ANIMATION_DURATION)
        }
        binding.graphView.invalidate()
    }
}