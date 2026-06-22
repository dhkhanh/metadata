package com.ebiz.metadata.infrastructure.persistence.mapper;

import com.ebiz.metadata.domain.schema.model.FieldDefinition;
import com.ebiz.metadata.infrastructure.persistence.entity.FieldDefinitionJpaEntity;

public final class FieldMapper {

    private FieldMapper() {
    }

    public static FieldDefinition toDomain(
            FieldDefinitionJpaEntity entity
    ) {

        if (entity == null) {
            return null;
        }

        return FieldDefinition.builder()
                .id(entity.getId())
                .entityId(entity.getEntityId())
                .fieldCode(entity.getFieldCode())
                .fieldName(entity.getFieldName())
                .dataType(entity.getDataType())
                .required(entity.getRequired())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static FieldDefinitionJpaEntity toJpa(
            FieldDefinition field
    ) {

        if (field == null) {
            return null;
        }

        return FieldDefinitionJpaEntity.builder()
                .id(field.getId())
                .entityId(field.getEntityId())
                .fieldCode(field.getFieldCode())
                .fieldName(field.getFieldName())
                .dataType(field.getDataType())
                .required(field.getRequired())
                .createdAt(field.getCreatedAt())
                .build();
    }
}