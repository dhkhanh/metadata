package com.ebiz.metadata.infrastructure.adapter.jpa;

import com.ebiz.metadata.domain.ui.model.ScreenDefinition;
import com.ebiz.metadata.domain.ui.repository.ScreenRepository;
import com.ebiz.metadata.infrastructure.datasource.entity.ScreenDefinitionJpaEntity;
import com.ebiz.metadata.infrastructure.datasource.repository.ScreenDefinitionJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ScreenRepositoryImpl implements ScreenRepository {

    private final ScreenDefinitionJpaRepository repository;

    @Override
    public ScreenDefinition save(
            ScreenDefinition screen
    ) {

        ScreenDefinitionJpaEntity entity =
                ScreenDefinitionJpaEntity.builder()
                        .id(screen.getId())
                        .screenCode(screen.getScreenCode())
                        .name(screen.getName())
                        .components(screen.getComponents())
                        .permissionCode(screen.getPermissionCode())
                        .createdAt(screen.getCreatedAt())
                        .build();

        ScreenDefinitionJpaEntity saved =
                repository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<ScreenDefinition> findById(
            UUID id
    ) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<ScreenDefinition> findByScreenCode(
            String screenCode
    ) {

        return repository.findByScreenCode(screenCode)
                .map(this::toDomain);
    }

    @Override
    public Optional<ScreenDefinition> findByPermissionCode(
            String permissionCode
    ) {

        return repository.findByPermissionCode(permissionCode)
                .stream()
                .findFirst()
                .map(this::toDomain);
    }

    @Override
    public List<ScreenDefinition> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(
            ScreenDefinition screen
    ) {

        repository.deleteById(
                screen.getId()
        );
    }

    private ScreenDefinition toDomain(
            ScreenDefinitionJpaEntity entity
    ) {

        return ScreenDefinition.builder()
                .id(entity.getId())
                .screenCode(entity.getScreenCode())
                .name(entity.getName())
                .components(entity.getComponents())
                .permissionCode(entity.getPermissionCode())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}