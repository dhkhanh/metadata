package com.ebiz.metadata.infrastructure.adapter.jpa;

import com.ebiz.metadata.domain.schema.model.FieldDefinition;
import com.ebiz.metadata.domain.schema.repository.FieldRepository;
import com.ebiz.metadata.infrastructure.datasource.entity.FieldDefinitionJpaEntity;
import com.ebiz.metadata.infrastructure.datasource.repository.FieldDefinitionJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FieldRepositoryImpl implements FieldRepository {

    private final FieldDefinitionJpaRepository repository;

    @Override
    public FieldDefinition save(FieldDefinition field) {

        FieldDefinitionJpaEntity saved =
                repository.save(
                        FieldDefinitionJpaEntity.builder()
                                .id(field.getId())
                                .entityId(field.getEntityId())
                                .fieldCode(field.getFieldCode())
                                .fieldName(field.getFieldName())
                                .dataType(field.getDataType())
                                .required(field.isRequired())
                                .createdAt(field.getCreatedAt())
                                .build()
                );

        return toDomain(saved);
    }

    @Override
    public Optional<FieldDefinition> findById(UUID id) {
        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<FieldDefinition> findByEntityId(UUID entityId) {
        return repository.findByEntityId(entityId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<FieldDefinition> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(FieldDefinition field) {
        repository.deleteById(field.getId());
    }

    private FieldDefinition toDomain(
            FieldDefinitionJpaEntity entity
    ) {

        return FieldDefinition.builder()
                .id(entity.getId())
                .entityId(entity.getEntityId())
                .fieldCode(entity.getFieldCode())
                .fieldName(entity.getFieldName())
                .dataType(entity.getDataType())
                .required(entity.isRequired())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}