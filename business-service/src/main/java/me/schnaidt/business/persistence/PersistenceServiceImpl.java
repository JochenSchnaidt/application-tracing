package me.schnaidt.business.persistence;

import lombok.extern.slf4j.Slf4j;
import me.schnaidt.business.model.SomeBusinessObject;
import me.schnaidt.business.persistence.domain.BusinessEntity;
import me.schnaidt.business.persistence.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
public class PersistenceServiceImpl implements PersistenceService {

  @Autowired
  private BusinessRepository businessRepository;

  @Override
  public Optional<SomeBusinessObject> getById(Long id) {
    return businessRepository.findById(id).map(this::mapFromEntity);
  }

  @Override
  public SomeBusinessObject persist(SomeBusinessObject someBusinessObject) {
    BusinessEntity businessEntity = mapToEntity(someBusinessObject);
    BusinessEntity persisted = businessRepository.save(businessEntity);
    return mapFromEntity(persisted);
  }

  SomeBusinessObject mapFromEntity(BusinessEntity entity) {
    SomeBusinessObject model = new SomeBusinessObject();
    model.setId(entity.getId());
    model.setName(entity.getName());
    model.setSomeValue(entity.getSomeValue());
    model.setOtherValue(entity.getOtherValue());
    model.setCreated(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(entity.getCreated()));
    return model;
  }

  BusinessEntity mapToEntity(SomeBusinessObject model) {
    BusinessEntity entity = new BusinessEntity();

    if (null != model.getId()) {
      entity.setId(model.getId());
    }

    entity.setName(model.getName());
    entity.setSomeValue(model.getSomeValue());
    entity.setOtherValue(model.getOtherValue());

    if (StringUtils.hasText(model.getCreated())) {
      entity.setCreated(LocalDateTime.parse(model.getCreated(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    } else {
      entity.setCreated(LocalDateTime.now());
    }

    return entity;
  }

}
