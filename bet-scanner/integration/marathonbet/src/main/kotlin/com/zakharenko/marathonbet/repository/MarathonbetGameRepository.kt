package com.zakharenko.marathonbet.repository

import com.zakharenko.marathonbet.model.MarathonbetGame
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class MarathonbetGameRepository(
  private val dslContext: DSLContext,
) {

  fun save(marathonbetGame: MarathonbetGame): Mono<Void> {
    return Mono.fromCallable {
      dslContext.insertInto(DSL.table("marathonbet_game"))
        .set(DSL.field("date"), marathonbetGame.date)
        .set(DSL.field("tournament"), marathonbetGame.tournament)
        .set(DSL.field("sport_kind"), marathonbetGame.sportKind)
        .set(DSL.field("reference"), marathonbetGame.reference)
        .execute()
    }
      .then()
  }
}
