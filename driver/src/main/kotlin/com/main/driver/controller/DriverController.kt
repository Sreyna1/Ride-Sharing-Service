package com.main.driver.controller

import com.main.driver.model.Driver
import com.main.driver.service.DriverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/driver")
class DriverController {

    @Autowired lateinit var service: DriverService

    @GetMapping("/list")
    fun list(@RequestParam(required = false) query: String?, @RequestParam page: Int, @RequestParam size: Int): MutableMap<String, Any?> {
        val driverList = service.list(query, page, size)
        val driver = driverList.content
        val totalElement = driverList.totalElements
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to driver, "total" to totalElement
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): MutableMap<String, Any?> {
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to service.find(id)
        )
    }

    @PostMapping("/create")
    fun add(@RequestBody driver: Driver): MutableMap<String, Any?> {
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to service.add(driver)
        )
    }

    @PutMapping("update")
    fun update(@RequestBody driver: Driver): MutableMap<String, Any?> {
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to service.update(driver)
        )
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): MutableMap<String, Any> {
        service.delete(id)
        return mutableMapOf("response" to mapOf("code" to 200, "message" to "Deleted"))
    }
}