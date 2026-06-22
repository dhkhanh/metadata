package com.ebiz.metadata.domain.ui.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenComponent {
    private UUID id;
    private UUID screenId;
    /**
     * FORM
     * VIEW
     * REPORT
     * DASHBOARD
     */
    private String componentType;
    private UUID componentId;
    private Integer displayOrder;
    /**
     * HEADER
     * LEFT
     * CENTER
     * RIGHT
     * FOOTER
     */
    private String region;
}