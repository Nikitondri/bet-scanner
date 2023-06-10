package com.zakharenko.gateway.marathonbet.model

data class MarathonbetGame(
  var id: Long? = null,
  var date: String? = null,
  var tournament: String? = null,
  var sportKind: String? = null,
  var reference: String? = null,
)
