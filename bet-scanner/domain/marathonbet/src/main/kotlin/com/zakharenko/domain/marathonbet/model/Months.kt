package com.zakharenko.domain.marathonbet.model

enum class Months(
  private val num: Int,
  private val ruName: String,
) {
  JANUARY(1, "янв"),
  FEBRUARY(2, "фев"),
  MARCH(3, "мар"),
  APRIL(4, "апр"),
  MAY(5, "май"),
  JUNE(6, "июн"),
  JULY(7, "июл"),
  AUGUST(8, "авг"),
  SEPTEMBER(9, "сен"),
  OCTOBER(10, "окт"),
  NOVEMBER(11, "ноя"),
  DECEMBER(12, "дек");

  fun getNum(): Int {
    return this.num
  }

  fun getRuName(): String {
    return this.ruName
  }
}
