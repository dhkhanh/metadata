package com.ebiz.metadata.infrastructure.persistence.mapper;

import com.ebiz.metadata.domain.ui.model.FormDefinition;
import com.ebiz.metadata.infrastructure.persistence.entity.FormDefinitionJpaEntity;

public final class FormMapper {

    private FormMapper() {
    }

    public static FormDefinition toDomain(
            FormDefinitionJpaEntity entity
    ) {

        if (entity == null) {
            return null;
        }

        return FormDefinition.builder()
                .id(entity.getId())
                .entityId(entity.getEntityId())
                .formCode(entity.getFormCode())
                .name(entity.getName())
                .fieldsConfig(entity.getFieldsConfig())
                .layoutConfig(entity.getLayoutConfig())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static FormDefinitionJpaEntity toJpa(
            FormDefinition form
    ) {

        if (form == null) {
            return null;
        }

        return FormDefinitionJpaEntity.builder()
                .id(form.getId())
                .entityId(form.getEntityId())
                .formCode(form.getFormCode())
                .name(form.getName())
                .fieldsConfig(form.getFieldsConfig())
                .layoutConfig(form.getLayoutConfig())
                .createdAt(form.getCreatedAt())
                .build();
    }
}