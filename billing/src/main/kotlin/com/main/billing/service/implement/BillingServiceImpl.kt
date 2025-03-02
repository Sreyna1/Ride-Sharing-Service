package com.main.billing.service.implement

import com.main.billing.exception.NotFoundException
import com.main.billing.model.Billing
import com.main.billing.repository.BillingRepository
import com.main.billing.service.BillingService
import jakarta.persistence.criteria.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class BillingServiceImpl : BillingService {

    @Autowired lateinit var repository: BillingRepository

    override fun list(query: String?, page: Int, size: Int): Page<Billing> {
        return repository.findAll({ root, _, cb ->
            val predicates = ArrayList<Predicate>()
            if (query != null) {
                val name = cb.like(cb.upper(root.get("name")), "%${query.uppercase(Locale.getDefault())}%")
                predicates.add(name)
            }
            predicates.add(cb.isTrue(root.get("status")))
            cb.and(*predicates.toTypedArray())
        }, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")))
    }

    override fun find(id: Long): Billing? = repository.findByIdAndStatusTrue(id) ?: throw NotFoundException("Not found")

    override fun findAll(): List<Billing>? = repository.findAllByStatusTrueOrderByIdAsc()

    override fun add(t: Billing): Billing = repository.save(t)

    override fun update(t: Billing): Billing {
        val billing = find(t.id!!)
        billing?.amount = t.amount
        return repository.save(billing!!)
    }

    override fun delete(id: Long) = repository.deleteById(id)
}