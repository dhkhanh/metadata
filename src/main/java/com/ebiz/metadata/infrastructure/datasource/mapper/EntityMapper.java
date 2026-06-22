package com.ebiz.metadata.infrastructure.persistence.mapper;

import com.ebiz.metadata.domain.schema.model.EntityDefinition;
import com.ebiz.metadata.infrastructure.persistence.entity.EntityDefinitionJpaEntity;

public final class EntityMapper {

    private EntityMapper() {
    }

    public static EntityDefinition toDomain(
            EntityDefinitionJpaEntity entity
    ) {

        if (entity == null) {
            return null;
        }

        return EntityDefinition.builder()
                .id(entity.getId())
                .entityCode(entity.getEntityCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static EntityDefinitionJpaEntity toJpa(
            EntityDefinition entity
    ) {

        if (entity == null) {
            return null;
        }

        return EntityDefinitionJpaEntity.builder()
                .id(entity.getId())
                .entityCode(entity.getEntityCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}