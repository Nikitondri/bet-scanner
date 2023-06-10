package com.zakharenko.marathonbet.connector

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class MarathonbetConnector(
  private val webClient: WebClient,
) {

  fun getPageHtml(endpoint: String): Mono<String> {
    return webClient.get()
      .uri(endpoint)
      .retrieve()
      .bodyToMono(String::class.java)
  }
}
