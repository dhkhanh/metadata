package com.ebiz.metadata.domain.schema.repository;

import com.ebiz.metadata.domain.schema.model.FieldDefinition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FieldRepository {

    FieldDefinition save(FieldDefinition field);

    Optional<FieldDefinition> findById(UUID id);

    List<FieldDefinition> findAll();
    List<FieldDefinition> findByEntityCode(String entityCode);

    boolean existsByEntityCodeAndFieldName(
            String entityCode,
            String fieldName
    );

    void delete(FieldDefinition field);

}