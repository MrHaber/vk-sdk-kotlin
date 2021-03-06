package com.example.vkbot

import com.petersamokhin.vksdk.core.http.HttpClientConfig
import com.petersamokhin.vksdk.http.VkKtorHttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * Example implementation of the HTTP client based on ktor.
 *
 * @param config Basic configurations
 */
class CioKtorHttpClient(
    config: HttpClientConfig = HttpClientConfig()
): VkKtorHttpClient(config) {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    /**
     * Instantiate the desired client and apply the basic configurations
     *
     * @param config Basic configurations
     * @return Desired HTTP client engine, e.g. CIO, etc.
     */
    @OptIn(KtorExperimentalAPI::class)
    override fun createEngineWithConfig(config: HttpClientConfig): HttpClientEngine {
        return CIO.create {
            endpoint {
                connectTimeout = config.connectTimeout.toLong()
                requestTimeout = config.readTimeout.toLong()
            }
        }
    }
}