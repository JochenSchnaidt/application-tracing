package me.schnaidt.business.persistence.repository;

import me.schnaidt.business.persistence.domain.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {

}
