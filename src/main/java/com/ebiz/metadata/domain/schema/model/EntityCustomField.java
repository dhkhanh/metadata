// EntityCustomField.java
package com.ebiz.metadata.domain.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record EntityCustomField(
        UUID id,                    // ID định danh duy nhất
        String entityCode,          // Mã thực thể (ví dụ: ORDER, PRODUCT)
        String fieldCode,           // Mã trường dữ liệu tùy chỉnh
        String fieldLabel,          // Tên hiển thị của trường trên giao diện
        String dataType,            // Kiểu dữ liệu (ví dụ: STRING, INTEGER, BOOLEAN)
        boolean required,           // Trạng thái bắt buộc nhập hay không
        boolean searchable,         // Có cho phép tìm kiếm theo trường này không
        boolean visible,            // Có hiển thị trường này trên giao diện không
        String defaultValue,        // Giá trị mặc định nếu không nhập
        int displayOrder,           // Thứ tự hiển thị trên giao diện
        String description,         // Mô tả chi tiết về trường
        boolean active,             // Trạng thái hoạt động của trường
        LocalDateTime createdAt,    // Thời điểm tạo
        LocalDateTime updatedAt     // Thời điểm cập nhật cuối cùng
) {}