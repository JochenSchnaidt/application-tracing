package me.schnaidt.tracing;

public interface TraceService {

  void logTraceInformation(String requestDetails, String responseDetails);
}
