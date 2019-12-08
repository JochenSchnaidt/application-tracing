package me.schnaidt.tracing.aspect;

public interface TraceService {

  void logTraceInformation(String requestDetails, String responseDetails);
}
