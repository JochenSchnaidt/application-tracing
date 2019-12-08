package me.schnaidt.tracing.database;

import lombok.extern.slf4j.Slf4j;
import me.schnaidt.tracing.database.domain.TracingEntity;
import me.schnaidt.tracing.database.repository.TracingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class DatabaseTracingServiceImpl implements DatabaseTracingService {

  @Autowired
  private TracingRepository tracingRepository;

  @Override
  public void foo(String requestDetails, String responseDetails) {

    log.info("db layer reached");

    TracingEntity entity = new TracingEntity();
    entity.setRequest(requestDetails);
    entity.setResponse(responseDetails);
    entity.setCreated(LocalDateTime.now());

    tracingRepository.save(entity);

    log.info("db layer reached done");
  }

}
