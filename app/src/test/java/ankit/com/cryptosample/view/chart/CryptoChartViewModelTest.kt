package ankit.com.cryptosample.view.chart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ankit.com.cryptosample.PresentationTestData
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.ChartsDomainModel
import ankit.com.domain.usecase.GetChartUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by AnkitSingh on 3/27/21.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CryptoChartViewModelTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getChartUseCase: GetChartUseCase

    @Mock
    private lateinit var chartObserver: Observer<ApiResponse<ChartsDomainModel>>

    private lateinit var viewModel: CryptoChartViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CryptoChartViewModel(getChartUseCase)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get bitcoin chart usecase returns chart data success scenario`() =
        testCoroutineScope.runBlockingTest {
            val testChartData = PresentationTestData.getBitcoinChartsData()

            whenever(getChartUseCase("market-price")).thenReturn(flowOf(ApiResponse.Success(testChartData)))

            viewModel.getBitcoinChart("market-price")
            viewModel.chart.observeForever(chartObserver)

            verify(chartObserver).onChanged(ApiResponse.Success(testChartData))

            viewModel.chart.removeObserver(chartObserver)
        }

    @Test
    fun `get bitcoin chart usecase returns error scenario`() =
        testCoroutineScope.runBlockingTest {
            val exception = Exception()

            whenever(getChartUseCase("market-price")).thenReturn(flowOf(ApiResponse.Error(exception)))

            viewModel.getBitcoinChart("market-price")
            viewModel.chart.observeForever(chartObserver)

            verify(chartObserver).onChanged(ApiResponse.Error(exception))

            viewModel.chart.removeObserver(chartObserver)
        }
}