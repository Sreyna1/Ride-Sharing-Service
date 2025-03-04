package com.main.passenger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class PassengerApplication

fun main(args: Array<String>) {
    runApplication<PassengerApplication>(*args)
}
