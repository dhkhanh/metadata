package com.ebiz.metadata.infrastructure.adapter.jpa;

import com.ebiz.metadata.domain.ui.model.FormDefinition;
import com.ebiz.metadata.domain.ui.repository.FormRepository;
import com.ebiz.metadata.infrastructure.datasource.entity.FormDefinitionJpaEntity;
import com.ebiz.metadata.infrastructure.datasource.repository.FormDefinitionJpaRepository;

import com.ebiz.metadata.infrastructure.datasource.mapper.FormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FormRepositoryImpl implements FormRepository {

    private final FormDefinitionJpaRepository repository;

    @Override
    public FormDefinition save(FormDefinition form) {
        return FormMapper.toDomain(
            repository.save(
                    FormMapper.toJpa(form)
            )
        );
    }

    @Override
    public Optional<FormDefinition> findById(UUID id) {
        return repository.findById(id)
            .map(FormMapper::toDomain);
    }

    @Override
    public Optional<FormDefinition> findByFormCode(
            String formCode
    ) {

        return repository.findByFormCode(formCode)
                .map(this::toDomain);
    }

    @Override
    public List<FormDefinition> findByEntityId(
            UUID entityId
    ) {

        return repository.findByEntityId(entityId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<FormDefinition> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(
            FormDefinition form
    ) {

        repository.deleteById(
                form.getId()
        );
    }

    private FormDefinition toDomain(
            FormDefinitionJpaEntity entity
    ) {

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
}