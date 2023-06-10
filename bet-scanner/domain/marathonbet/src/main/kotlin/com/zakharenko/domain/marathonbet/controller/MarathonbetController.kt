package com.zakharenko.domain.marathonbet.controller

import com.zakharenko.domain.marathonbet.model.MarathonbetGame
import com.zakharenko.domain.marathonbet.service.MarathonbetService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/marathonbet")
@CrossOrigin(origins = ["http://localhost:63342"])
class MarathonbetController(
  private val marathonbetService: MarathonbetService,
) {

  @GetMapping("/")
  fun findAll(): Flux<MarathonbetGame> {
    return marathonbetService.findAll()
  }
}
