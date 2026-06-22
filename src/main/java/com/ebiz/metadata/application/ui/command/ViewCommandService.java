package com.ebiz.metadata.application.ui.command;

import com.ebiz.metadata.application.ui.dto.ViewDto;
import com.ebiz.metadata.domain.ui.model.ViewDefinition;
import com.ebiz.metadata.domain.ui.repository.ViewRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ViewCommandService {

    private final ViewRepository viewRepository;

    /**
     * Tạo View
     */
    public ViewDto create(
            UUID entityId,
            String viewCode,
            String name,
            String columnsConfig,
            String filterConfig
    ) {
        // Kiểm tra trùng view_code
        if(viewRepository.findByViewCode(viewCode)
                .isPresent()) {
            throw new RuntimeException(
                    "View code already exists: "
                    + viewCode
            );
        }

        ViewDefinition view = ViewDefinition.builder()
                .id(UUID.randomUUID())
                .entityId(entityId)
                .viewCode(viewCode)
                .name(name)
                .columnsConfig(columnsConfig)
                .filterConfig(filterConfig)
                .createdAt(LocalDateTime.now())
                .build();

        viewRepository.save(view);

        return ViewDto.from(view);
    }

    /**
     * Cập nhật View
     */
    public ViewDto update(
            UUID id,
            String viewCode,
            String name,
            String columnsConfig,
            String filterConfig
    ) {

        ViewDefinition view =
                viewRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "View not found: " + id
                                ));
        
        // Kiểm tra đổi view_code
        if(!view.getViewCode()
                .equals(viewCode)) {
            viewRepository.findByViewCode(viewCode)
                    .ifPresent(existing -> {
                        if(!existing.getId()
                                .equals(id)) {
                            throw new RuntimeException(
                                    "View code already exists: "
                                    + viewCode
                            );
                        }
                    });
        }

        view.setViewCode(viewCode);
        view.setName(name);
        view.setColumnsConfig(columnsConfig);
        view.setFilterConfig(filterConfig);

        viewRepository.save(view);

        return ViewDto.from(view);
    }

    /**
     * Xóa View
     */
    public void delete(UUID id) {

        ViewDefinition view =
                viewRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "View not found: " + id
                                ));

        viewRepository.delete(view);
    }
}