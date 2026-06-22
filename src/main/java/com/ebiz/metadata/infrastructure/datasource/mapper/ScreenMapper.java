package com.ebiz.metadata.infrastructure.persistence.mapper;

import com.ebiz.metadata.domain.ui.model.ScreenDefinition;
import com.ebiz.metadata.infrastructure.persistence.entity.ScreenDefinitionJpaEntity;

public final class ScreenMapper {

    private ScreenMapper() {
    }

    public static ScreenDefinition toDomain(
            ScreenDefinitionJpaEntity entity
    ) {

        if (entity == null) {
            return null;
        }

        return ScreenDefinition.builder()
                .id(entity.getId())
                .screenCode(entity.getScreenCode())
                .name(entity.getName())
                .components(entity.getComponents())
                .permissionCode(entity.getPermissionCode())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static ScreenDefinitionJpaEntity toJpa(
            ScreenDefinition screen
    ) {

        if (screen == null) {
            return null;
        }

        return ScreenDefinitionJpaEntity.builder()
                .id(screen.getId())
                .screenCode(screen.getScreenCode())
                .name(screen.getName())
                .components(screen.getComponents())
                .permissionCode(screen.getPermissionCode())
                .createdAt(screen.getCreatedAt())
                .build();
    }
}