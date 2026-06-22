package com.ebiz.metadata.domain.schema.repository;

import com.ebiz.metadata.domain.schema.model.EntityCustomField;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomFieldRepository {

    EntityCustomField save(EntityCustomField field);

    Optional<EntityCustomField> findById(UUID id);

    List<EntityCustomField> findByEntityCode(String entityCode);
    List<EntityCustomField> findActiveByEntityCode(String entityCode);

    boolean existsByEntityCodeAndFieldCode(String entityCode,String fieldCode);
}