package com.ebiz.metadata.infrastructure.adapter.jpa;

import com.ebiz.metadata.domain.ui.model.ScreenComponent;
import com.ebiz.metadata.domain.ui.repository.ScreenComponentRepository;
import com.ebiz.metadata.infrastructure.datasource.entity.ScreenComponentJpaEntity;
import com.ebiz.metadata.infrastructure.datasource.repository.ScreenComponentJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ScreenComponentRepositoryImpl implements ScreenComponentRepository {

    private final ScreenComponentJpaRepository repository;

    @Override
    public ScreenComponent save(
            ScreenComponent component
    ) {

        ScreenComponentJpaEntity entity =
                ScreenComponentJpaEntity.builder()
                        .id(component.getId())
                        .screenId(component.getScreenId())
                        .componentType(component.getComponentType())
                        .componentId(component.getComponentId())
                        .displayOrder(component.getDisplayOrder())
                        .region(component.getRegion())
                        .build();

        ScreenComponentJpaEntity saved =
                repository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<ScreenComponent> findById(
            UUID id
    ) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<ScreenComponent> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<ScreenComponent> findByScreenId(
            UUID screenId
    ) {

        return repository
                .findByScreenIdOrderByDisplayOrderAsc(screenId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(
            ScreenComponent component
    ) {

        repository.deleteById(
                component.getId()
        );
    }

    private ScreenComponent toDomain(
            ScreenComponentJpaEntity entity
    ) {

        return ScreenComponent.builder()
                .id(entity.getId())
                .screenId(entity.getScreenId())
                .componentType(entity.getComponentType())
                .componentId(entity.getComponentId())
                .displayOrder(entity.getDisplayOrder())
                .region(entity.getRegion())
                .build();
    }
}