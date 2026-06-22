package com.ebiz.metadata.infrastructure.adapter.jpa;

import com.ebiz.metadata.domain.schema.model.EntityCustomField;
import com.ebiz.metadata.domain.schema.repository.CustomFieldRepository;
import com.ebiz.metadata.infrastructure.datasource.entity.CustomFieldJpaEntity;
import com.ebiz.metadata.infrastructure.datasource.repository.CustomFieldJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomFieldRepositoryImpl implements CustomFieldRepository {

    private final CustomFieldJpaRepository repository;

    @Override
    public EntityCustomField save(
            EntityCustomField field
    ) {

        EntityCustomFieldJpaEntity entity =
                EntityCustomFieldJpaEntity.builder()
                        .id(field.getId())
                        .entityId(field.getEntityId())
                        .fieldCode(field.getFieldCode())
                        .fieldName(field.getFieldName())
                        .dataType(field.getDataType())
                        .required(field.getRequired())
                        .defaultValue(field.getDefaultValue())
                        .validationRule(field.getValidationRule())
                        .createdAt(field.getCreatedAt())
                        .build();

        EntityCustomFieldJpaEntity saved =
                repository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<EntityCustomField> findById(
            UUID id
    ) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<EntityCustomField> findByEntityId(
            UUID entityId
    ) {

        return repository.findByEntityId(entityId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(
            EntityCustomField field
    ) {

        repository.deleteById(
                field.getId()
        );
    }

    private EntityCustomField toDomain(
            EntityCustomFieldJpaEntity entity
    ) {

        return EntityCustomField.builder()
                .id(entity.getId())
                .entityId(entity.getEntityId())
                .fieldCode(entity.getFieldCode())
                .fieldName(entity.getFieldName())
                .dataType(entity.getDataType())
                .required(entity.getRequired())
                .defaultValue(entity.getDefaultValue())
                .validationRule(entity.getValidationRule())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}