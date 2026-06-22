package com.ebiz.metadata.domain.ui.repository;

import com.ebiz.metadata.domain.ui.model.ViewDefinition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ViewRepository {

    ViewDefinition save(ViewDefinition view);

    Optional<ViewDefinition> findById(UUID id);
    Optional<ViewDefinition> findByViewCode(String viewCode);

    List<ViewDefinition> findAll();
    List<ViewDefinition> findByEntityId(UUID entityId);

    void delete(ViewDefinition view);
}