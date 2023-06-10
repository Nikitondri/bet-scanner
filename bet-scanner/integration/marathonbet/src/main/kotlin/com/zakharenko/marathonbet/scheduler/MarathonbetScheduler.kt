package com.zakharenko.marathonbet.scheduler

import com.zakharenko.marathonbet.service.MarathonbetService
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import reactor.core.scheduler.Schedulers

class MarathonbetScheduler(
  private val marathonbetService: MarathonbetService,
) {

  @Scheduled(fixedDelay = 60000000)
  fun runCode() {
    marathonbetService.process()
      .subscribeOn(Schedulers.boundedElastic())
      .subscribe()
  }
}
