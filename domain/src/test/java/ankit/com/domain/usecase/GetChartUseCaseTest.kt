package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.core.successOr
import ankit.com.domain.repository.BlockChainChartsRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import testdata.DomainTestData.getBitcoinChartsData
import java.io.IOException

/**
 * Created by AnkitSingh on 3/28/21.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetChartUseCaseTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var blockChainChartsRepository: BlockChainChartsRepository

    private lateinit var testCase: GetChartUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        testCase = GetChartUseCase(blockChainChartsRepository, testCoroutineDispatcher)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `bitcoin chart info is returned by repository success scenario`() = testCoroutineScope.runBlockingTest {
        val expectedResult =  flowOf(ApiResponse.Success(getBitcoinChartsData()))
        whenever(blockChainChartsRepository.getChartInformation("market-price")).thenReturn(expectedResult)

        val result = testCase.invoke("market-price")

        result.collect {data ->
            assertEquals(getBitcoinChartsData(), data.successOr(null))
        }
    }

    @Test
    fun  `repository returns error when exception is thrown while fetching chart info`()  = testCoroutineScope.runBlockingTest{
        val expectedError =  ApiResponse.Error(IOException())
        whenever(blockChainChartsRepository.getChartInformation("market-price")).thenReturn(
            flowOf(expectedError)
        )

        val result = testCase.invoke("market-price")

        result.collect{ error ->
            assertEquals(expectedError, error)
        }
    }
}