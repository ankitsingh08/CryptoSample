package ankit.com.data.repository

import ankit.com.data.model.toDomainModel
import ankit.com.data.remote.BlockChainService
import ankit.com.domain.core.ApiResponse
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import testdata.TestData

/**
 * Created by AnkitSingh on 3/28/21.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BlockChainStatsRepositoryImplTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var blockChainService: BlockChainService

    private lateinit var blockChainStatsRepository: BlockChainStatsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        blockChainStatsRepository = BlockChainStatsRepositoryImpl(blockChainService)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get bitcoin stats returns stats success scenario`()  = testCoroutineScope.runBlockingTest {
        val testData = TestData.getBitcoinStats()
        val expectedOutput = ApiResponse.Success(testData.toDomainModel())
        whenever(blockChainService.getBitcoinStats()).thenReturn(testData)

        val flow = blockChainStatsRepository.getBitcoinStats()

        // Verify
        flow.collect { data->
            assertEquals(expectedOutput, data)
        }
    }

    @Test
    fun `get bitcoin stats returns stats Error scenario`()  = testCoroutineScope.runBlockingTest {
        val exception = NullPointerException()
        val expectedOutput = ApiResponse.Error(exception)
        whenever(blockChainService.getBitcoinStats()).thenThrow(exception)

        val flow = blockChainStatsRepository.getBitcoinStats()

        // Verify
        flow.collect{ data ->
            assertEquals(expectedOutput, data)
        }
    }

}