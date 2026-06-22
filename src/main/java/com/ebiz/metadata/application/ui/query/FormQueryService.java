package com.ebiz.metadata.application.ui.query;

import com.ebiz.metadata.application.ui.dto.FormDto;
import com.ebiz.metadata.domain.ui.model.FormDefinition;
import com.ebiz.metadata.domain.ui.repository.FormRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FormQueryService {

    private final FormRepository formRepository;

    /**
     * Lấy tất cả Form
     */
    public List<FormDto> findAll() {

        return formRepository.findAll()
                .stream()
                .map(FormDto::from)
                .toList();
    }

    /**
     * Lấy Form theo ID
     */
    public FormDto getById(UUID id) {

        FormDefinition form =
                formRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Form not found: " + id
                                ));

        return FormDto.from(form);
    }

    /**
     * Lấy danh sách Form theo Entity
     */
    public List<FormDto> findByEntityId(
            UUID entityId
    ) {

        return formRepository.findByEntityId(entityId)
                .stream()
                .map(FormDto::from)
                .toList();
    }
}