package com.github.gabriel.resource_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class ResourceServerApplication

fun main(args: Array<String>) {
	runApplication<ResourceServerApplication>(*args)
}
