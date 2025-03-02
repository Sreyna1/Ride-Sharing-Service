package com.main.passenger.model

import com.main.passenger.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "mas_passenger")
class Passenger : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0
    var name: String? = null
    var phone: String? = null
}