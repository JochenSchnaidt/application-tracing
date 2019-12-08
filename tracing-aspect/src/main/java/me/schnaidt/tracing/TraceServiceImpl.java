package me.schnaidt.tracing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TraceServiceImpl implements TraceService {

  @Override
  public void logTraceInformation(String requestDetails, String responseDetails) {

    log.info(requestDetails);
    log.info(responseDetails);

  }

}
