package me.schnaidt.business.persistence;

import me.schnaidt.business.model.SomeBusinessObject;

import java.util.Optional;

public interface PersistenceService {

  Optional<SomeBusinessObject> getById(Long id);

  SomeBusinessObject persist(SomeBusinessObject someBusinessObject);

}
