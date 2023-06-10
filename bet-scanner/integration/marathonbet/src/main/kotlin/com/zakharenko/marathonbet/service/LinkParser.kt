package com.zakharenko.marathonbet.service

import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class LinkParser(
  @Value("\${marathonbet.links.limit}") private val limit: Int,
) {

  fun parse(html: String, endpoint: String, condition: (String) -> Boolean): List<String> {
    val doc = Jsoup.parse(html)
    val result = mutableListOf<String>()

    val elements = doc.select("a[href*=$endpoint]")
    for (el in elements) {
      val href = el.attr("href")
      if (condition(href)) {
        result.add(href)
      }
    }

    return result.take(limit)
  }
}
