package com.ebiz.metadata.domain.ui.repository;

import com.ebiz.metadata.domain.ui.model.FormDefinition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FormRepository {

    FormDefinition save(FormDefinition form);

    Optional<FormDefinition> findById(UUID id);
    Optional<FormDefinition> findByFormCode(String formCode);

    List<FormDefinition> findAll();
    List<FormDefinition> findByEntityId(UUID entityId);

    void delete(FormDefinition form);

}