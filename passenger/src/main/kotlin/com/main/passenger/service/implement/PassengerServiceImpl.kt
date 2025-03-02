package com.main.passenger.service.implement

import com.main.passenger.exception.NotFoundException
import com.main.passenger.model.Passenger
import com.main.passenger.repository.PassengerRepository
import com.main.passenger.service.PassengerService
import jakarta.persistence.criteria.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class PassengerServiceImpl : PassengerService {

    @Autowired lateinit var repository: PassengerRepository

    override fun list(query: String?, page: Int, size: Int): Page<Passenger> {
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

    override fun find(id: Long): Passenger? = repository.findByIdAndStatusTrue(id) ?: throw NotFoundException("Not found")

    override fun findAll(): List<Passenger>? = repository.findAllByStatusTrueOrderByIdAsc()

    override fun add(t: Passenger): Passenger = repository.save(t)

    override fun update(t: Passenger): Passenger {
        val passenger = find(t.id!!)
        passenger?.name = t.name

        return repository.save(passenger!!)
    }

    override fun delete(id: Long) = repository.deleteById(id)
}