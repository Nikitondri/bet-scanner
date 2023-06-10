package com.zakharenko.domain.marathonbet.config

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.DriverManager

@Configuration
class JooqConfig {

  @Value("\${spring.datasource.url}")
  private lateinit var url: String

  @Value("\${spring.datasource.username}")
  private lateinit var username: String

  @Value("\${spring.datasource.password}")
  private lateinit var password: String

  @Bean
  fun dslContext(): DSLContext {
    val url = this.url
    val username = this.username
    val password = this.password

    val connection = DriverManager.getConnection(url, username, password)

    return DSL.using(connection, SQLDialect.MYSQL)
  }
}
