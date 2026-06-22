package com.ebiz.metadata.domain.ui.repository;

import com.ebiz.metadata.domain.ui.model.ScreenComponent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreenComponentRepository {

    ScreenComponent save(ScreenComponent component);

    Optional<ScreenComponent> findById(UUID id);

    List<ScreenComponent> findAll();
    List<ScreenComponent> findByScreenId(UUID screenId);

    void delete(ScreenComponent component);
    void deleteByScreenId(UUID screenId);
    long countByScreenId(UUID screenId);
}