package com.main.passenger.service

import com.main.passenger.model.Passenger
import org.springframework.data.domain.Page

interface PassengerService {
    fun list(query: String?, page: Int, size: Int): Page<Passenger>
    fun find(id: Long): Passenger?
    fun findAll(): List<Passenger>?
    fun add(t: Passenger): Passenger
    fun update(t: Passenger): Passenger
    fun delete(id: Long)
}