package me.schnaidt.tracing;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class TraceAspect {

  @Autowired
  private TraceService traceService;

  @Around("@annotation(TraceParameter)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
    Method method = methodSignature.getMethod();

    Object returnValue = joinPoint.proceed();

    log.info("- - -");
    log.info("Target [{}] method [{}]", joinPoint.getTarget().getClass().getName(), method.getName());
    log.info(request.getMethod());
    request.getParameterMap().forEach((k, v) -> log.info("Parameter {}, value {}", k, v));
    log.info(returnValue.toString());
    log.info("- - -");

    String requestDetails = "Request: target [" + joinPoint.getTarget().getClass().getName() + "] method [" + method.getName() + "] via " + request.getMethod();
    String responseDetails = "Response: " + returnValue.toString();
    traceService.logTraceInformation(requestDetails, responseDetails);

    return returnValue;
  }

}
