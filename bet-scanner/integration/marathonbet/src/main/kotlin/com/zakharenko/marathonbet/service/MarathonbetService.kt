package com.zakharenko.marathonbet.service

import com.zakharenko.marathonbet.connector.MarathonbetConnector
import com.zakharenko.marathonbet.model.SportKinds
import com.zakharenko.marathonbet.repository.MarathonbetGameRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MarathonbetService {
  fun process(): Flux<Void>
}

@Service
class DefaultMarathonbetService(
  private val gameParser: GameParser,
  private val marathonbetConnector: MarathonbetConnector,
  private val linkParser: LinkParser,
  private val marathonbetGameRepository: MarathonbetGameRepository,
  @Value("\${marathonbet.base.url}") private val baseUrl: String,
  @Value("\${marathonbet.base.endpoint}") private val mainPageEndpoint: String,
) : MarathonbetService {

  override fun process(): Flux<Void> {
    return marathonbetConnector.getPageHtml(mainPageEndpoint)
      .flatMapMany(::process)
  }

  private fun process(mainPageHtml: String): Flux<Void> {
    return Flux.fromIterable(SportKinds.values().toList())
      .flatMap { kind -> createMarathonbetGames(mainPageHtml, kind) }
  }

  private fun createMarathonbetGames(html: String, kind: SportKinds): Flux<Void> {
    return Flux.fromIterable(linkParser.parse(html, kind.getEndpoint()) { it.split("/").size == 5 })
      .flatMap { tournamentEndpoint -> marathonbetConnector.getPageHtml(tournamentEndpoint) }
      .flatMapIterable { page -> linkParser.parse(page, kind.getEndpoint()) { it.split("/").size > 5 } }
      .flatMap { gameEndpoint -> createGame(gameEndpoint, kind) }
  }

  private fun createGame(gameLink: String, kind: SportKinds): Mono<Void> {
    return marathonbetConnector.getPageHtml(gameLink)
      .flatMap { page -> gameParser.parse(page) }
      .map { game ->
        game.apply {
          this.reference = "$baseUrl$gameLink"
          this.sportKind = kind.name
        }
      }
      .flatMap(marathonbetGameRepository::save)
  }
}
