package ankit.com.cryptosample.view.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ankit.com.cryptosample.R
import ankit.com.cryptosample.databinding.CryptoStatsLayoutBinding
import ankit.com.cryptosample.mapper.toPresentation
import ankit.com.domain.core.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.crypto_stats_layout.*

/**
 * Created by AnkitSingh on 3/22/21.
 */
const val CHART_NAME = "chartName"
const val MARKET_PRICE = "market-price"
const val AVG_BLOCK_SIZE = "avg-block-size"
const val NO_OF_TRANSACTIONS = "n-transactions"
@AndroidEntryPoint
class CryptoStatsFragment : Fragment() {

    private val cryptoStatsViewModel: CryptoStatsViewModel by viewModels()

    private lateinit var binding: CryptoStatsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CryptoStatsLayoutBinding.inflate(inflater, container, false)
        initializeUI()
        return binding.root
    }

    private fun initializeUI() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.app_name)

        cryptoStatsViewModel.getBitcoinStats()
        cryptoStatsViewModel.stats.observe(viewLifecycleOwner, Observer { apiResponse->
            apiResponse?.let {
                when (apiResponse) {
                    is ApiResponse.Loading -> stats_progress_bar.visibility = View.VISIBLE
                    is ApiResponse.Success -> {
                        stats_progress_bar.visibility = View.GONE
                        price_cardview.visibility = View.VISIBLE
                        block_size_cardview.visibility = View.VISIBLE
                        no_of_transactions_cardview.visibility = View.VISIBLE
                        binding.statsModel = apiResponse.data.toPresentation()
                    }
                    is ApiResponse.Error -> {
                        stats_progress_bar.visibility = View.GONE
                        tv_error_view.visibility = View.VISIBLE
                    }
                }
            }
        })

        binding.priceLayout.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(CHART_NAME, MARKET_PRICE)
            findNavController().navigate(R.id.crypto_chart_fragment, bundle)
        }

        binding.blockSizeLayout.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(CHART_NAME, AVG_BLOCK_SIZE)
            findNavController().navigate(R.id.crypto_chart_fragment, bundle)
        }

        binding.noOfTransactionsLayout.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(CHART_NAME, NO_OF_TRANSACTIONS)
            findNavController().navigate(R.id.crypto_chart_fragment, bundle)
        }
    }

}