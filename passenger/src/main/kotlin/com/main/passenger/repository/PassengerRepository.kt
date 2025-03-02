package com.main.passenger.repository

import com.main.passenger.model.Passenger
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface PassengerRepository: JpaRepository<Passenger, Long>, JpaSpecificationExecutor<Passenger> {
    fun findByIdAndStatusTrue(id: Long): Passenger?
    fun findAllByStatusTrueOrderByIdAsc(): List<Passenger>?
}