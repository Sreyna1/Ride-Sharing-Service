package com.main.billing.service

import com.main.billing.model.Billing
import org.springframework.data.domain.Page

interface BillingService {
    fun list(query: String?, page: Int, size: Int): Page<Billing>
    fun find(id: Long): Billing?
    fun findAll(): List<Billing>?
    fun add(t: Billing): Billing
    fun update(t: Billing): Billing
    fun delete(id: Long)
}