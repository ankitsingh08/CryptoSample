package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.core.successOr
import ankit.com.domain.repository.BlockChainStatsRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import testdata.DomainTestData.getBitcoinStats
import java.io.IOException

/**
 * Created by AnkitSingh on 3/28/21.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetBitcoinStatsUseCaseTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var blockChainStatsRepository: BlockChainStatsRepository

    private lateinit var testCase: GetBitcoinStatsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        testCase = GetBitcoinStatsUseCase(blockChainStatsRepository, testCoroutineDispatcher)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `bitcoin stats is returned by repository success scenario`() = testCoroutineScope.runBlockingTest {
        val expectedResult =  flowOf(ApiResponse.Success(getBitcoinStats()))
        whenever(blockChainStatsRepository.getBitcoinStats()).thenReturn(expectedResult)

        val result = testCase.invoke(null)

        result.collect {data ->
            assertEquals(getBitcoinStats(), data.successOr(null))
        }
    }

    @Test
    fun  `repository returns error when exception is thrown while fetching bitcoin stats`()  = testCoroutineScope.runBlockingTest{
        val expectedError =  ApiResponse.Error(IOException())
        whenever(blockChainStatsRepository.getBitcoinStats()).thenReturn(
            flowOf(expectedError)
        )

        val result = testCase.invoke(null)

        result.collect{ error ->
            assertEquals(expectedError, error)
        }
    }
}