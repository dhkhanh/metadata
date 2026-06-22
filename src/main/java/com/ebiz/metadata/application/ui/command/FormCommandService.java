package com.ebiz.metadata.application.ui.command;

import com.ebiz.metadata.application.ui.dto.FormDto;
import com.ebiz.metadata.domain.ui.model.FormDefinition;
import com.ebiz.metadata.domain.ui.repository.FormRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FormCommandService {

    private final FormRepository formRepository;

    /**
     * Tạo Form
     */
    public FormDto create(
            UUID entityId,
            String formCode,
            String name,
            String fieldsConfig,
            String layoutConfig
    ) {
         // Kiểm tra trùng form_code
        if(formRepository.findByFormCode(formCode).isPresent()) {

            throw new RuntimeException(
                    "Form code already exists: "
                    + formCode
            );
        }

        FormDefinition form = FormDefinition.builder()
                .id(UUID.randomUUID())
                .entityId(entityId)
                .formCode(formCode)
                .name(name)
                .fieldsConfig(fieldsConfig)
                .layoutConfig(layoutConfig)
                .createdAt(LocalDateTime.now())
                .build();

        formRepository.save(form);

        return FormDto.from(form);
    }

    /**
     * Cập nhật Form
     */
    public FormDto update(
            UUID id,
            String formCode,
            String name,
            String fieldsConfig,
            String layoutConfig
    ) {

        FormDefinition form =
                formRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Form not found: " + id
                                ));
        /* Kiểm tra form_code
         * Nếu người dùng đổi mã form
         * thì phải đảm bảo mã mới chưa tồn tại */
        if(!form.getFormCode().equals(formCode)) {
            formRepository.findByFormCode(formCode)
                    .ifPresent(existingForm -> {
                        if(!existingForm.getId()
                                .equals(id)) {
                            throw new RuntimeException(
                                    "Form code already exists: "
                                    + formCode
                            );
                        }
                    });
        }
        form.setFormCode(formCode);                        
        form.setName(name);
        form.setFieldsConfig(fieldsConfig);
        form.setLayoutConfig(layoutConfig);

        formRepository.save(form);

        return FormDto.from(form);
    }

    /**
     * Xóa Form
     */
    public void delete(UUID id) {

        FormDefinition form =
                formRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Form not found: " + id
                                ));

        formRepository.delete(form);
    }

}