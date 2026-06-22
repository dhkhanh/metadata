package com.ebiz.metadata.application.schema.dto;

import com.ebiz.metadata.domain.schema.model.EntityDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityDto {

    private UUID id;
      
    @Column(nullable = false, unique = true, length = 100)
    private String entityCode; /*** Mã Entity: employee, customer, vendor...*/  
    
    @Column(nullable = false)
    private String entityName; /*** Tên Entity hiển thị */

    /*** Class Java tương ứng */
    @Column(nullable = false, length = 500)
    private String entityClass;

    @Column(nullable = false, length = 100)
    private String serviceName; /*** Service sở hữu dữ liệu */

    @Column(length = 100)
    private String schemaName; /*** Schema DB */
   
    @Column(nullable = false, length = 100)
    private String tableName; /*** Tên bảng DB */
    
    @Column(nullable = false, length = 100)
    private String primaryKeyField; /*** Tên khóa chính */
    
    @Column(nullable = false, length = 50)
    private String primaryKeyType; /*** Kiểu khóa chính */

    /**
     * Trường hiển thị chính
     * Ví dụ:
     * employee -> fullName
     * customer -> customerName
     */
    private String displayNameField;
    
    private Boolean softDelete; /*** Có hỗ trợ Soft Delete không*/   
    private Boolean tenantEnabled; /*** Có hỗ trợ Multi Tenant không*/    
    private Boolean auditable; /*** Có Audit Log không */
    private Boolean cacheable; /*** Có Cache không */

    private String apiPath; /*** API CRUD động */
   
    private String description; /*** Mô tả */

    private Boolean active; /*** Kích hoạt */

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static EntityDto from(
        EntityDefinition entity
    ) {

        return EntityDto.builder()
                .id(entity.getId())
                .entityCode(entity.getEntityCode())
                .entityName(entity.getEntityName())
                .entityClass(entity.getEntityClass())
                .serviceName(entity.getServiceName())
                .schemaName(entity.getSchemaName())
                .tableName(entity.getTableName())
                .primaryKeyField(entity.getPrimaryKeyField())
                .primaryKeyType(entity.getPrimaryKeyType())
                .displayNameField(entity.getDisplayNameField())
                .softDelete(entity.getSoftDelete())
                .tenantEnabled(entity.getTenantEnabled())
                .auditable(entity.getAuditable())
                .cacheable(entity.getCacheable())
                .apiPath(entity.getApiPath())
                .description(entity.getDescription())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}