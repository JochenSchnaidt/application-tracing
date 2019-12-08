package me.schnaidt.tracing.database.repository;


import me.schnaidt.tracing.database.domain.TracingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracingRepository extends JpaRepository<TracingEntity, Long> {
}
