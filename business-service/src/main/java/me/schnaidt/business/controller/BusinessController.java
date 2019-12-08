package me.schnaidt.business.controller;

import lombok.extern.slf4j.Slf4j;
import me.schnaidt.business.model.SomeBusinessObject;
import me.schnaidt.business.persistence.PersistenceService;
import me.schnaidt.tracing.aspect.TraceParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "bo", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessController {

  @Autowired
  private PersistenceService persistenceService;

  @TraceParameter
  @GetMapping("/{id}")
  public SomeBusinessObject getById(@PathVariable Long id) {

    log.info("get request for id {}", id);

    Optional<SomeBusinessObject> optObject = persistenceService.getById(id);

    if (optObject.isEmpty()) {
      log.info("not found");
      // raise exception and let the handler return 404
    }

    return optObject.get();
  }

  @TraceParameter
  @PostMapping(path = "/tbd", consumes = MediaType.APPLICATION_JSON_VALUE)
  SomeBusinessObject persist(@RequestBody SomeBusinessObject someBusinessObject) {

    log.info("post request for {}", someBusinessObject);

    SomeBusinessObject persisted = persistenceService.persist(someBusinessObject);
    log.info(persisted.toString());

    return persisted;
  }

}
