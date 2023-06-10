package com.zakharenko.marathonbet.controller

import com.zakharenko.marathonbet.service.MarathonbetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/dev")
class DevController(
  private val marathonbetService: MarathonbetService,
) {

  @GetMapping("/start-uploading")
  fun test(): Flux<Void> {
    return marathonbetService.process()
  }
}
