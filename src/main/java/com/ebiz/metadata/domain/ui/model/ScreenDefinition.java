// ScreenDefinition.java
// ScreenDefinition -> Ghép nhiều Form/View thành 1 màn hình ERP hoàn chỉnh

package com.ebiz.metadata.domain.model.ui;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDefinition{
        private UUID id,                    // ID định danh duy nhất của màn hình
        private String screenCode;  
        private String name,                // Tên màn hình (ví dụ: "Màn hình quản lý kho")
        private Map<String, Object> components, // Danh sách các thành phần (Form/View) có trong màn hình
        private String permissionCode,      // Mã quyền truy cập màn hình (ví dụ: "SCREEN_WAREHOUSE_VIEW")
        private LocalDateTime createdAt     // Thời điểm tạo màn hình
}