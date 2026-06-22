package com.ebiz.metadata.infrastructure.persistence.mapper;

import com.ebiz.metadata.domain.ui.model.ViewDefinition;
import com.ebiz.metadata.infrastructure.persistence.entity.ViewDefinitionJpaEntity;

public final class ViewMapper {

    private ViewMapper() {
    }

    public static ViewDefinition toDomain(
            ViewDefinitionJpaEntity entity
    ) {

        if (entity == null) {
            return null;
        }

        return ViewDefinition.builder()
                .id(entity.getId())
                .entityId(entity.getEntityId())
                .viewCode(entity.getViewCode())
                .name(entity.getName())
                .columnsConfig(entity.getColumnsConfig())
                .filterConfig(entity.getFilterConfig())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static ViewDefinitionJpaEntity toJpa(
            ViewDefinition view
    ) {

        if (view == null) {
            return null;
        }

        return ViewDefinitionJpaEntity.builder()
                .id(view.getId())
                .entityId(view.getEntityId())
                .viewCode(view.getViewCode())
                .name(view.getName())
                .columnsConfig(view.getColumnsConfig())
                .filterConfig(view.getFilterConfig())
                .createdAt(view.getCreatedAt())
                .build();
    }
}