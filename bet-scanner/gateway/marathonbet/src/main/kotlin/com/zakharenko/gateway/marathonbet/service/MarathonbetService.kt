package com.zakharenko.gateway.marathonbet.service

import com.zakharenko.gateway.marathonbet.model.MarathonbetGame
import com.zakharenko.gateway.marathonbet.model.Months
import com.zakharenko.gateway.marathonbet.repository.MarathonbetGameRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class MarathonbetService(
  private val marathonbetGameRepository: MarathonbetGameRepository,
) {

  fun findAll(): Flux<MarathonbetGame> {
    return marathonbetGameRepository.findAll()
      .doOnNext { game -> game.date?.let { game.date = buildDate(it) } }
  }

  private fun buildDate(date: String): String {
    return LocalDateTime.of(
      LocalDate.now().year,
      Months.values().first { it.getRuName() == date.substring(3, 6) }.getNum(),
      date.substring(0, 2).toInt(),
      date.substring(7, 9).toInt(),
      date.substring(10, 12).toInt(),
      0,
    ).toString()
  }
}
