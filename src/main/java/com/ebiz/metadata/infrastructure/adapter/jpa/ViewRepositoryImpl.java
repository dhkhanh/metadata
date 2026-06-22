package com.ebiz.metadata.infrastructure.adapter.jpa;

import com.ebiz.metadata.domain.ui.model.ViewDefinition;
import com.ebiz.metadata.domain.ui.repository.ViewRepository;
import com.ebiz.metadata.infrastructure.datasource.entity.ViewDefinitionJpaEntity;
import com.ebiz.metadata.infrastructure.datasource.repository.ViewDefinitionJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ViewRepositoryImpl implements ViewRepository {

    private final ViewDefinitionJpaRepository repository;

    @Override
    public ViewDefinition save(
            ViewDefinition view
    ) {

        ViewDefinitionJpaEntity entity =
                ViewDefinitionJpaEntity.builder()
                        .id(view.getId())
                        .entityId(view.getEntityId())
                        .viewCode(view.getViewCode())
                        .name(view.getName())
                        .columnsConfig(view.getColumnsConfig())
                        .filterConfig(view.getFilterConfig())
                        .createdAt(view.getCreatedAt())
                        .build();

        ViewDefinitionJpaEntity saved =
                repository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<ViewDefinition> findById(
            UUID id
    ) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<ViewDefinition> findByViewCode(
            String viewCode
    ) {

        return repository.findByViewCode(viewCode)
                .map(this::toDomain);
    }

    @Override
    public List<ViewDefinition> findByEntityId(
            UUID entityId
    ) {

        return repository.findByEntityId(entityId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<ViewDefinition> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(
            ViewDefinition view
    ) {

        repository.deleteById(
                view.getId()
        );
    }

    private ViewDefinition toDomain(
            ViewDefinitionJpaEntity entity
    ) {

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
}