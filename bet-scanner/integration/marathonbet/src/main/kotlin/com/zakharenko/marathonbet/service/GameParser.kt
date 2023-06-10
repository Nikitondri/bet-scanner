package com.zakharenko.marathonbet.service

import com.zakharenko.marathonbet.model.MarathonbetGame
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2

@Service
class GameParser {

  fun parse(html: String): Mono<MarathonbetGame> {
    return Mono.zip(
      parseDate(html),
      parseTournament(html)
    )
      .map { (date, tournament) ->
        MarathonbetGame(
          date = date,
          tournament = tournament,
        )
      }
  }

  private fun parseDate(html: String): Mono<String> {
    return Mono.fromCallable { Jsoup.parse(html) }
      .mapNotNull<Element> { document ->
        document.selectFirst("td.date.date-with-month").takeIf { it != null } ?:
        document.selectFirst("td.date.date-short")
      }
      .map { element -> element.text().replace(Regex("[\\s\\n]+"), " ") }
      .filter { date -> date.length > 5 }
  }

  private fun parseTournament(html: String): Mono<String> {
    return Mono.fromCallable { Jsoup.parse(html) }
      .mapNotNull { document -> document.select("h2.category-label span.nowrap") }
      .map { elements -> elements.joinToString(" ") { it.text() } }
  }
}
