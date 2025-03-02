package com.main.driver.model

import com.main.driver.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "mas_driver")
class Driver : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0
    var name: String? = null
}