// ViewDefinition.java
// ViewDefinition -> Sinh màn hình danh sách

package com.ebiz.metadata.domain.model.ui;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewDefinition{
        private UUID id,                    // ID định danh duy nhất của view
        private UUID entityId,              // ID thực thể mà view này áp dụng (tham chiếu tới EntityDefinition)
        private String viewCode;            // Mã duy nhất của View (ví dụ: CUSTOMER_LIST, PRODUCT_LIST)
        private String name,                // Tên định danh của view (ví dụ: "OrderListView")
        private Map<String, Object> columnsConfig, // Cấu hình các cột hiển thị: độ rộng, thứ tự, ẩn/hiện
        private Map<String, Object> filterConfig,  // Cấu hình các điều kiện lọc mặc định
        private LocalDateTime createdAt     // Thời điểm tạo view
}