package com.ebiz.metadata.infrastructure.adapter.jpa;

import com.ebiz.metadata.domain.schema.model.EntityDefinition;
import com.ebiz.metadata.domain.schema.repository.EntityRepository;
import com.ebiz.metadata.infrastructure.datasource.entity.EntityDefinitionJpaEntity;
import com.ebiz.metadata.infrastructure.datasource.repository.EntityDefinitionJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EntityRepositoryImpl implements EntityRepository {

    private final EntityDefinitionJpaRepository repository;

    @Override
    public EntityDefinition save(EntityDefinition entity) {

        EntityDefinitionJpaEntity saved =
                repository.save(
                        EntityDefinitionJpaEntity.builder()
                                .id(entity.getId())
                                .entityCode(entity.getEntityCode())
                                .name(entity.getName())
                                .description(entity.getDescription())
                                .createdAt(entity.getCreatedAt())
                                .build()
                );

        return toDomain(saved);
    }

    @Override
    public Optional<EntityDefinition> findById(UUID id) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<EntityDefinition> findByEntityCode(String entityCode) {

        return repository.findByEntityCode(entityCode)
                .map(this::toDomain);
    }

    @Override
    public List<EntityDefinition> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(EntityDefinition entity) {

        repository.deleteById(entity.getId());
    }

    private EntityDefinition toDomain(
            EntityDefinitionJpaEntity entity
    ) {

        return EntityDefinition.builder()
                .id(entity.getId())
                .entityCode(entity.getEntityCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}