package me.schnaidt.business.persistence;

import lombok.extern.slf4j.Slf4j;
import me.schnaidt.business.model.SomeBusinessObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PersistenceServiceImpl implements PersistenceService {

  @Override
  public Optional<SomeBusinessObject> getById(Long id) {
    return Optional.empty();
  }

  @Override
  public SomeBusinessObject persist(SomeBusinessObject someBusinessObject) {
    return someBusinessObject;
  }

}
