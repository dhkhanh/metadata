package com.ebiz.metadata.application.ui.dto;

import com.ebiz.metadata.domain.ui.model.ScreenComponent;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenComponentDto {

    private UUID id;
    private UUID screenId;
    private String componentType;
    private UUID componentId;
    private Integer displayOrder;
    private String region;

    public static ScreenComponentDto from(
            ScreenComponent component
    ) {
        return ScreenComponentDto.builder()
                .id(component.getId())
                .screenId(component.getScreenId())
                .componentType(component.getComponentType())
                .componentId(component.getComponentId())
                .displayOrder(component.getDisplayOrder())
                .region(component.getRegion())
                .build();
    }
}