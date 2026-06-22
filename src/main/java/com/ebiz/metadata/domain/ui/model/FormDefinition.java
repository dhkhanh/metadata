// FormDefinition.java
// FormDefinition -> Sinh màn hình nhập liệu

package com.ebiz.metadata.domain.model.ui;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormDefinition{
        private UUID id,                    // ID định danh duy nhất của form
        private UUID entityId,              // ID thực thể mà form này áp dụng (tham chiếu tới EntityDefinition)
        private String name,                // Tên định danh của form (ví dụ: "OrderCreateForm")
        private String formCode,            // Mã duy nhất của Form (ví dụ: CUSTOMER_CREATE, INVOICE_ENTRY)
        private Map<String, Object> fieldsConfig, // Cấu hình các trường: loại input, validation, rules
        private Map<String, Object> layoutConfig, // Cấu hình bố cục: sắp xếp, phân cột, chia tab
        private LocalDateTime createdAt     // Thời điểm tạo form
}