package com.ebiz.metadata.domain.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record EntityDefinition(
        UUID id,                    // ID định danh duy nhất
        String entityCode,          // Mã định danh thực thể (duy nhất)
        String entityName,          // Tên thực thể
        String entityClass,         // Tên class Java tương ứng (đầy đủ package)
        String serviceName,         // Tên dịch vụ (service) quản lý thực thể này
        String schemaName,          // Tên lược đồ (database schema) - nếu có
        String tableName,           // Tên bảng trong cơ sở dữ liệu
        String primaryKeyField,     // Tên trường khóa chính (mặc định 'id')
        String primaryKeyType,      // Kiểu dữ liệu của khóa chính (ví dụ: UUID, LONG)
        String displayNameField,    // Trường dùng làm tên hiển thị đại diện cho record
        boolean softDelete,         // Cho phép xóa mềm (không xóa hẳn khỏi DB)
        boolean tenantEnabled,      // Hỗ trợ đa người thuê (multi-tenancy)
        boolean auditable,          // Tự động ghi lại lịch sử thay đổi (Audit log)
        boolean cacheable,          // Cho phép lưu cache để tăng hiệu năng
        String apiPath,             // Đường dẫn API mặc định cho thực thể
        String description,         // Mô tả chi tiết về thực thể
        boolean active,             // Trạng thái hoạt động
        LocalDateTime createdAt,    // Thời điểm tạo
        LocalDateTime updatedAt     // Thời điểm cập nhật cuối cùng
) {
    // Bạn có thể thêm các phương thức logic nghiệp vụ (Domain Logic) vào đây
    // để đảm bảo tính nhất quán của dữ liệu.
}