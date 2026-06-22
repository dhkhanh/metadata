package com.ebiz.metadata.application.ui.dto;

import com.ebiz.metadata.domain.ui.model.FormDefinition;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class FormDto {

    private UUID id;
    private UUID entityId;
    private String formCode; /*** Mã duy nhất của Form * Ví dụ:* CUSTOMER_CREATE | * INVOICE_ENTRY */
    private String name;
    private String fieldsConfig;
    private String layoutConfig;
    private LocalDateTime createdAt;

    public static FormDto from(
            FormDefinition form
    ) {

        return FormDto.builder()
                .id(form.getId())
                .entityId(form.getEntityId())
                .formCode(form.formCode())
                .name(form.getName())
                .fieldsConfig(form.getFieldsConfig())
                .layoutConfig(form.getLayoutConfig())
                .createdAt(form.getCreatedAt())
                .build();
    }
}