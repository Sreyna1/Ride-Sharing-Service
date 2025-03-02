package com.main.driver.service

import com.main.driver.model.Driver
import org.springframework.data.domain.Page

interface DriverService {
    fun list(query: String?, page: Int, size: Int): Page<Driver>
    fun find(id: Long): Driver?
    fun findAll(): List<Driver>?
    fun add(t: Driver): Driver
    fun update(t: Driver): Driver
    fun delete(id: Long)
}