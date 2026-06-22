package com.ebiz.metadata.application.ui.command;

import com.ebiz.metadata.application.ui.dto.ScreenComponentDto;
import com.ebiz.metadata.domain.ui.model.ScreenComponent;
import com.ebiz.metadata.domain.ui.repository.ScreenComponentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScreenComponentCommandService {

    private final ScreenComponentRepository repository;

    public ScreenComponentDto create(
            ScreenComponentDto dto
    ) {

        ScreenComponent component =
                ScreenComponent.builder()
                        .id(UUID.randomUUID())
                        .screenId(dto.getScreenId())
                        .componentType(dto.getComponentType())
                        .componentId(dto.getComponentId())
                        .displayOrder(dto.getDisplayOrder())
                        .region(dto.getRegion())
                        .build();

        repository.save(component);

        return ScreenComponentDto.from(component);
    }

    public ScreenComponentDto update(
            UUID id,
            ScreenComponentDto dto
    ) {

        ScreenComponent component =
                repository.findById(id)
                        .orElseThrow();

        component.setScreenId(dto.getScreenId());
        component.setComponentType(dto.getComponentType());
        component.setComponentId(dto.getComponentId());
        component.setDisplayOrder(dto.getDisplayOrder());
        component.setRegion(dto.getRegion());

        repository.save(component);

        return ScreenComponentDto.from(component);
    }

    public void delete(UUID id) {

        ScreenComponent component =
                repository.findById(id)
                        .orElseThrow();

        repository.delete(component);
    }
}