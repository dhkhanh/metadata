package com.ebiz.metadata.application.schema.dto;

import com.ebiz.metadata.domain.schema.model.EntityCustomField;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomFieldDto {

    private UUID id;
    private String entityCode;
    private String fieldCode;
    private String fieldLabel;
    private String dataType;
    private Boolean required;
    private Boolean searchable;
    private Boolean visible;
    private String defaultValue;
    private Integer displayOrder;
    private String description;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CustomFieldDto from(
            EntityCustomField field
    ) {

        return CustomFieldDto.builder()
                .id(field.getId())
                .entityCode(field.getEntityCode())
                .fieldCode(field.getFieldCode())
                .fieldLabel(field.getFieldLabel())
                .dataType(field.getDataType())
                .required(field.getRequired())
                .searchable(field.getSearchable())
                .visible(field.getVisible())
                .defaultValue(field.getDefaultValue())
                .displayOrder(field.getDisplayOrder())
                .description(field.getDescription())
                .active(field.getActive())
                .createdAt(field.getCreatedAt())
                .updatedAt(field.getUpdatedAt())
                .build();
    }
}