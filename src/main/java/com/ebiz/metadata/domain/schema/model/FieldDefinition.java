// FieldDefinition.java
package com.ebiz.metadata.domain.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record FieldDefinition(
        UUID id,                    // ID định danh duy nhất của trường
        String entityCode,          // Mã thực thể mà trường này thuộc về
        String fieldName,           // Tên kỹ thuật của trường (tên cột trong DB)
        String fieldLabel,          // Tên hiển thị trên giao diện người dùng
        String dataType,            // Kiểu dữ liệu (ví dụ: STRING, INTEGER, DATE)
        boolean required,           // Có bắt buộc nhập hay không
        boolean uniqueField,        // Có yêu cầu tính duy nhất không
        boolean searchable,         // Có cho phép tìm kiếm theo trường này không
        boolean sortable,           // Có cho phép sắp xếp theo trường này không
        boolean visible,            // Có hiển thị trên giao diện không
        int displayOrder,           // Thứ tự hiển thị (càng nhỏ càng đứng trước)
        String description,         // Mô tả chi tiết mục đích của trường
        LocalDateTime createdAt,    // Thời điểm tạo
        LocalDateTime updatedAt     // Thời điểm cập nhật cuối cùng
) {}