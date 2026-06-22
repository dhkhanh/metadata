package com.ebiz.metadata.infrastructure.datasource.repository;

import com.ebiz.metadata.infrastructure.datasource.entity.ScreenDefinitionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScreenDefinitionJpaRepository extends JpaRepository<ScreenDefinitionJpaEntity, UUID> {

    Optional<ScreenDefinitionJpaEntity> findByScreenCode(String screenCode)
    
    // 1. Tìm màn hình theo tên (hữu ích cho việc kiểm tra trùng lặp)
    Optional<ScreenDefinitionJpaEntity> findByName(String name);

    // 2. Tìm danh sách màn hình theo mã quyền (phân quyền hiển thị menu/nút)
    List<ScreenDefinitionJpaEntity> findByPermissionCode(String permissionCode);

    // 3. Tìm kiếm gần đúng theo tên màn hình (hữu ích cho thanh tìm kiếm)
    List<ScreenDefinitionJpaEntity> findByNameContainingIgnoreCase(String name);

    // 4. Tìm các màn hình có chứa một permissionCode cụ thể (truy vấn danh sách quyền)
    // Trường hợp permission_code không phải là null
    List<ScreenDefinitionJpaEntity> findByPermissionCodeIsNotNull();
}