package com.main.billing.controller

import com.main.billing.model.Billing
import com.main.billing.service.BillingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/billing")
class BillingController {

    @Autowired
    lateinit var service: BillingService

    @GetMapping("/list")
    fun list(@RequestParam(required = false) query: String?, @RequestParam page: Int, @RequestParam size: Int): MutableMap<String, Any?> {
        val billingList = service.list(query, page, size)
        val billing = billingList.content
        val totalElement = billingList.totalElements
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to billing, "total" to totalElement
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
    fun add(@RequestBody billing: Billing): MutableMap<String, Any?> {
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to service.add(billing)
        )
    }

    @PutMapping("update")
    fun update(@RequestBody billing: Billing): MutableMap<String, Any?> {
        return mutableMapOf(
            "response" to mapOf("code" to 200, "message" to "Success"),
            "results" to service.update(billing)
        )
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): MutableMap<String, Any> {
        service.delete(id)
        return mutableMapOf("response" to mapOf("code" to 200, "message" to "Deleted"))
    }
}