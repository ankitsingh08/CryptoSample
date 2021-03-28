package ankit.com.cryptosample.di

import ankit.com.data.repository.BlockChainChartRepositoryImpl
import ankit.com.data.repository.BlockChainStatsRepositoryImpl
import ankit.com.domain.repository.BlockChainChartsRepository
import ankit.com.domain.repository.BlockChainStatsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by AnkitSingh on 3/22/21.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBlockChainRepository(blockChainRepository: BlockChainStatsRepositoryImpl): BlockChainStatsRepository

    @Binds
    @Singleton
    abstract fun bindBlockChainChartsRepository(blockChainChartsRepository: BlockChainChartRepositoryImpl): BlockChainChartsRepository

}