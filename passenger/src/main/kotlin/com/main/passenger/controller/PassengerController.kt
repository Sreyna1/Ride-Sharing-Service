package com.main.passenger.controller

import com.main.passenger.model.Passenger
import com.main.passenger.service.PassengerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/passenger")
class PassengerController {

    @Autowired
    lateinit var service: PassengerService

    @GetMapping("/list")
    fun list(@RequestParam(required = false) query: String?, @RequestParam page: Int, @RequestParam size: Int): MutableMap<String, Any?> {
        val passengerList = service.list(query, page, size)
        val passenger = passengerList.content
        val totalElement = passengerList.totalElements
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to passenger, "total" to totalElement
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
    fun add(@RequestBody passenger: Passenger): MutableMap<String, Any?> {
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to service.add(passenger)
        )
    }

    @PutMapping("update")
    fun update(@RequestBody passenger: Passenger): MutableMap<String, Any?> {
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to service.update(passenger)
        )
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): MutableMap<String, Any> {
        service.delete(id)
        return mutableMapOf("response" to mapOf("code" to 200, "message" to "Deleted"))
    }
}