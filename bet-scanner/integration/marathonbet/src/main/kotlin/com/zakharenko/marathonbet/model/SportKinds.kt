package com.zakharenko.marathonbet.model

enum class SportKinds(private val endpoint: String) {
  FOOTBALL("su/betting/Football"),
  TENNIS("su/betting/Tennis");
  //Other sport kinds

  fun getEndpoint(): String {
    return endpoint
  }
}
