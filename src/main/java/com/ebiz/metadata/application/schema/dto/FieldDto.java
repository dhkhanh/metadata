package com.ebiz.metadata.application.schema.dto;

import com.ebiz.metadata.domain.schema.model.FieldDefinition;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FieldDto {

    private UUID id;
    private String entityCode;
    private String fieldName;
    private String fieldLabel;
    private String dataType;
    private Boolean required;
    private Boolean uniqueField;
    private Boolean searchable;
    private Boolean sortable;
    private Boolean visible;
    private Integer displayOrder;
    private String description;

    public static FieldDto from(
            FieldDefinition field
    ) {

        return FieldDto.builder()
                .id(field.getId())
                .entityCode(field.getEntityCode())
                .fieldName(field.getFieldName())
                .fieldLabel(field.getFieldLabel())
                .dataType(field.getDataType())
                .required(field.getRequired())
                .uniqueField(field.getUniqueField())
                .searchable(field.getSearchable())
                .sortable(field.getSortable())
                .visible(field.getVisible())
                .displayOrder(field.getDisplayOrder())
                .description(field.getDescription())
                .build();
    }

}