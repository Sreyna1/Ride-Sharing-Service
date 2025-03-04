package com.main.driver.service.implement

import com.main.driver.exception.NotFoundException
import com.main.driver.model.Driver
import com.main.driver.repository.DriverRepository
import com.main.driver.service.DriverService
import jakarta.persistence.criteria.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class DriverServiceImpl :  DriverService {

    @Autowired lateinit var repository: DriverRepository

    override fun list(query: String?, page: Int, size: Int): Page<Driver> {
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

    override fun find(id: Long): Driver? = repository.findByIdAndStatusTrue(id) ?: throw NotFoundException("Not found")

    override fun findAll(): List<Driver>? = repository.findAllByStatusTrueOrderByIdAsc()

    override fun add(t: Driver): Driver = repository.save(t)

    override fun update(t: Driver): Driver {
        val driver = find(t.id!!)
        driver?.name = t.name

        return repository.save(driver!!)
    }

    override fun delete(id: Long) = repository.deleteById(id)
}