package com.main.billing.model

import com.main.billing.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "mas_billing")
class Billing : BaseEntity () {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0
    var amount: Long? = 0
}