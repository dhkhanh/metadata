package com.ebiz.metadata.application.ui.command;

import com.ebiz.metadata.application.ui.dto.ScreenDto;
import com.ebiz.metadata.domain.ui.model.ScreenDefinition;
import com.ebiz.metadata.domain.ui.repository.ScreenRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ScreenCommandService {

    private final ScreenRepository screenRepository;

    /**
     * Tạo Screen
     */
    public ScreenDto create(
            String screenCode,
            String name,
            String components,
            String permissionCode
    ) {

        // Kiểm tra trùng screen_code
        if(screenRepository.findByScreenCode(screenCode)
                .isPresent()) {
            throw new RuntimeException(
                    "Screen code already exists: "
                    + screenCode
            );
        }

        ScreenDefinition screen = ScreenDefinition.builder()
                .id(UUID.randomUUID())
                .screenCode(screenCode)
                .name(name)
                .components(components)
                .permissionCode(permissionCode)
                .createdAt(LocalDateTime.now())
                .build();

        screenRepository.save(screen);

        return ScreenDto.from(screen);
    }

    /**
     * Cập nhật Screen
     */
    public ScreenDto update(
            UUID id,
            String screenCode,
            String name,
            String components,
            String permissionCode
    ) {

        ScreenDefinition screen =
                screenRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Screen not found: " + id
                                ));
        
        // Kiểm tra đổi screen_code
        if(!screen.getScreenCode()
                .equals(screenCode)){
            screenRepository.findByScreenCode(screenCode)
                    .ifPresent(existing -> {
                        if(!existing.getId()
                                .equals(id)){
                            throw new RuntimeException(
                                    "Screen code already exists: "
                                    + screenCode
                            );
                        }
                    });
        }
        screen.setScreenCode(screenCode);
        screen.setName(name);
        screen.setComponents(components);
        screen.setPermissionCode(permissionCode);

        screenRepository.save(screen);

        return ScreenDto.from(screen);
    }

    /**
     * Xóa Screen
     */
    public void delete(UUID id) {

        ScreenDefinition screen =
                screenRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Screen not found: " + id
                                ));

        screenRepository.delete(screen);
    }

}