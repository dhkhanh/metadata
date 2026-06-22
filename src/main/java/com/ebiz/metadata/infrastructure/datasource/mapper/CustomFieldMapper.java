package com.ebiz.metadata.infrastructure.persistence.mapper;

import com.ebiz.metadata.domain.schema.model.EntityCustomField;
import com.ebiz.metadata.infrastructure.persistence.entity.EntityCustomFieldJpaEntity;

public final class CustomFieldMapper {

    private CustomFieldMapper() {
    }

    public static EntityCustomField toDomain(
            EntityCustomFieldJpaEntity entity
    ) {

        if (entity == null) {
            return null;
        }

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

    public static EntityCustomFieldJpaEntity toJpa(
            EntityCustomField field
    ) {

        if (field == null) {
            return null;
        }

        return EntityCustomFieldJpaEntity.builder()
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
    }
}