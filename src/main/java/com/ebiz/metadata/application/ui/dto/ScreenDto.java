package com.ebiz.metadata.application.ui.dto;

import com.ebiz.metadata.domain.ui.model.ScreenDefinition;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ScreenDto {

    private UUID id;
    /**
     * Business Key
     *
     * Ví dụ:
     * INVENTORY_RECEIPT
     * CUSTOMER_LIST
     */
    private String screenCode;
    private String name;
    private String components;
    private String permissionCode;
    private LocalDateTime createdAt;

    public static ScreenDto from(
            ScreenDefinition screen
    ) {

        return ScreenDto.builder()
                .id(screen.getId())
                .screenCode(screen.getScreenCode())
                .name(screen.getName())
                .components(screen.getComponents())
                .permissionCode(screen.getPermissionCode())
                .createdAt(screen.getCreatedAt())
                .build();
    }

}