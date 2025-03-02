package com.main.billing.repository

import com.main.billing.model.Billing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface BillingRepository: JpaRepository<Billing, Long>, JpaSpecificationExecutor<Billing> {
    fun findByIdAndStatusTrue(id: Long): Billing?
    fun findAllByStatusTrueOrderByIdAsc(): List<Billing>?
}