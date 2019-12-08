package me.schnaidt.tracing.aspect;

import lombok.extern.slf4j.Slf4j;
import me.schnaidt.tracing.database.DatabaseTracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TraceServiceImpl implements TraceService {

  @Autowired(required = false)
  private DatabaseTracingService databaseTracingService;

  @Override
  public void logTraceInformation(String requestDetails, String responseDetails) {

    log.info(requestDetails);
    log.info(responseDetails);

    if(null != databaseTracingService){
      log.info("DatabaseTracingService looded");
      databaseTracingService.foo(requestDetails, responseDetails);
    } else {
      log.info("DatabaseTracingService NOT loaded");
    }
  }

}
