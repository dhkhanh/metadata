package com.ebiz.metadata.domain.ui.repository;

import com.ebiz.metadata.domain.ui.model.ScreenDefinition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreenRepository {

    ScreenDefinition save(ScreenDefinition screen);

    Optional<ScreenDefinition> findById(UUID id);
    Optional<ScreenDefinition> findByPermissionCode(String permissionCode);
    Optional<ScreenDefinition> findByScreenCode(String screenCode);

    List<ScreenDefinition> findAll();

    void delete(ScreenDefinition screen);

}