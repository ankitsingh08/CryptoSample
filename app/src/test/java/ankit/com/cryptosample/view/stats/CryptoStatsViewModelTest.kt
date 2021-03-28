package ankit.com.cryptosample.view.stats

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ankit.com.cryptosample.PresentationTestData.getBitcoinStats
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.StatsDomainModel
import ankit.com.domain.usecase.GetBitcoinStatsUseCase
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
class CryptoStatsViewModelTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getBitcoinStatsUseCase: GetBitcoinStatsUseCase

    @Mock
    private lateinit var statsObserver: Observer<ApiResponse<StatsDomainModel>>

    private lateinit var viewModel: CryptoStatsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CryptoStatsViewModel(getBitcoinStatsUseCase)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get bitcoin stats usecase returns bitcoins stats success scenario`() =
        testCoroutineScope.runBlockingTest {
            val testStats = getBitcoinStats()

            whenever(getBitcoinStatsUseCase("")).thenReturn(flowOf(ApiResponse.Success(testStats)))

            viewModel.getBitcoinStats()
            viewModel.stats.observeForever(statsObserver)

            verify(statsObserver).onChanged(ApiResponse.Success(testStats))

            viewModel.stats.removeObserver(statsObserver)
    }

    @Test
    fun `get bitcoin stats usecase returns bitcoins stats error scenario`() =
        testCoroutineScope.runBlockingTest {
            val exception = Exception()

            whenever(getBitcoinStatsUseCase("")).thenReturn(flowOf(ApiResponse.Error(exception)))

            viewModel.getBitcoinStats()
            viewModel.stats.observeForever(statsObserver)

            verify(statsObserver).onChanged(ApiResponse.Error(exception))

            viewModel.stats.removeObserver(statsObserver)
        }

}