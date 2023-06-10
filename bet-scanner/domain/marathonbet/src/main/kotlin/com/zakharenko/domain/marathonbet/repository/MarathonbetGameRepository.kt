package com.zakharenko.domain.marathonbet.repository

import com.zakharenko.domain.marathonbet.model.MarathonbetGame
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class MarathonbetGameRepository(
  @Autowired private val dslContext: DSLContext,
) {

  fun findAll(): Flux<MarathonbetGame> {
    return Mono.fromCallable {
      dslContext.select()
        .from("marathonbet_game")
        .fetchInto(MarathonbetGame::class.java)
    }
      .flatMapIterable { it }
  }
}
