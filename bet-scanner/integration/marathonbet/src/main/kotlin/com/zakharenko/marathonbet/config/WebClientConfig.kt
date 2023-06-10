package com.zakharenko.marathonbet.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

  @Value("\${marathonbet.base.url}")
  private lateinit var baseUrl: String

  @Bean
  fun webClient(): WebClient {
    return WebClient.builder()
      .exchangeStrategies(getWebClientStrategies())
      .baseUrl(baseUrl)
      .build()
  }

  private fun getWebClientStrategies(): ExchangeStrategies {
    return ExchangeStrategies.builder()
      .codecs { codecs: ClientCodecConfigurer ->
        codecs.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)
      }
      .build()
  }
}
