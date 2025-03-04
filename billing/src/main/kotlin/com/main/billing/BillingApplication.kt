package com.main.billing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class BillingApplication

fun main(args: Array<String>) {
    runApplication<BillingApplication>(*args)
}
