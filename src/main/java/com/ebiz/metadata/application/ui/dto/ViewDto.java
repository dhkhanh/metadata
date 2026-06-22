package com.ebiz.metadata.application.ui.dto;

import com.ebiz.metadata.domain.ui.model.ViewDefinition;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ViewDto {

    private UUID id;
    private UUID entityId;
    private String viewCode;  /*** Mã duy nhất của View * Ví dụ: * CUSTOMER_LIST | * PRODUCT_LIST  */
    private String name;
    private String columnsConfig;
    private String filterConfig;
    private LocalDateTime createdAt;

    public static ViewDto from(
            ViewDefinition view
    ) {

        return ViewDto.builder()
                .id(view.getId())
                .entityId(view.getEntityId())
                .viewCode(view.getViewCode())
                .name(view.getName())
                .columnsConfig(view.getColumnsConfig())
                .filterConfig(view.getFilterConfig())
                .createdAt(view.getCreatedAt())
                .build();
    }
}