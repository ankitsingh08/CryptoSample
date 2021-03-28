package ankit.com.data.remote

import ankit.com.data.model.Charts
import ankit.com.data.model.Stats
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by AnkitSingh on 3/22/21.
 */
interface BlockChainService {

    companion object {
        const val BASE_URL = "https://api.blockchain.info/"
    }

    @GET("stats")
    suspend fun getBitcoinStats(): Stats

    @GET("charts/{name}")
    suspend fun getBitcoinChart(@Path("name") name: String): Charts
}