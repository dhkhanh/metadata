package com.ebiz.metadata.domain.schema.repository;

import com.erp.metadata.domain.schema.model.EntityDefinition;

import java.util.Optional;
import java.util.UUID;

public interface EntityRepository {

    EntityDefinition save(EntityDefinition entity);

    Optional<EntityDefinition> findById(UUID id);
    Optional<EntityDefinition> findByEntityCode(String entityCode);

    List<EntityDefinition> findAll();
    List<EntityDefinition> findByActiveTrue();

    boolean existsByEntityCode(String entityCode);

}