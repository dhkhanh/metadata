package com.ebiz.metadata.application.ui.query;

import com.ebiz.metadata.application.ui.dto.ViewDto;
import com.ebiz.metadata.domain.ui.model.ViewDefinition;
import com.ebiz.metadata.domain.ui.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewQueryService {

    private final ViewRepository viewRepository;

    /**
     * Lấy tất cả View
     */
    public List<ViewDto> findAll() {

        return viewRepository.findAll()
                .stream()
                .map(ViewDto::from)
                .toList();
    }

    /**
     * Lấy View theo ID
     */
    public ViewDto getById(UUID id) {

        ViewDefinition view =
                viewRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "View not found: " + id
                                ));

        return ViewDto.from(view);
    }

    /**
     * Lấy danh sách View theo Entity
     */
    public List<ViewDto> findByEntityId(
            UUID entityId
    ) {

        return viewRepository.findByEntityId(entityId)
                .stream()
                .map(ViewDto::from)
                .toList();
    }

    /**
     * Lấy View theo mã View
     */
    public ViewDto getByViewCode(String viewCode) {
        ViewDefinition view = viewRepository.findByViewCode(viewCode)
                .orElseThrow(() ->
                        new RuntimeException(
                                "View not found: " + viewCode
                        ));
        return ViewDto.from(view);
    }
}