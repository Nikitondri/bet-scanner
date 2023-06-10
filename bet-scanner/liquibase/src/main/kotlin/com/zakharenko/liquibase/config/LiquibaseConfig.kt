package com.zakharenko.liquibase.config

import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class LiquibaseConfig {

  @Autowired
  private lateinit var dataSource: DataSource

  @Bean
  fun liquibase(): SpringLiquibase {
    val liquibase = SpringLiquibase()
    liquibase.dataSource = dataSource
    liquibase.changeLog = "classpath:/db/changelog/changelog.xml"
    liquibase.isDropFirst = false
    return liquibase
  }
}
