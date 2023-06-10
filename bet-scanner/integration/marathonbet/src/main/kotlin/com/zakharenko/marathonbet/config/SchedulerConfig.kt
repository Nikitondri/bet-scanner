package com.zakharenko.marathonbet.config

import com.zakharenko.marathonbet.scheduler.MarathonbetScheduler
import com.zakharenko.marathonbet.service.MarathonbetService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class SchedulerConfig(
  private val marathonbetService: MarathonbetService,
) {

  @Bean
  fun marathonbetScheduler(): MarathonbetScheduler {
    return MarathonbetScheduler(
      marathonbetService = marathonbetService,
    )
  }
}
