package com.main.driver.repository

import com.main.driver.model.Driver
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface DriverRepository: JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {
    fun findByIdAndStatusTrue(id: Long): Driver?
    fun findAllByStatusTrueOrderByIdAsc(): List<Driver>?
}