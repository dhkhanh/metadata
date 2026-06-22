package com.ebiz.metadata.application.ui.query;

import com.ebiz.metadata.application.ui.dto.ScreenDto;
import com.ebiz.metadata.domain.ui.model.ScreenDefinition;
import com.ebiz.metadata.domain.ui.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScreenQueryService {

    private final ScreenRepository screenRepository;

    /**
     * Lấy tất cả Screen
     */
    public List<ScreenDto> findAll() {

        return screenRepository.findAll()
                .stream()
                .map(ScreenDto::from)
                .toList();
    }

    /**
     * Lấy Screen theo ID
     */
    public ScreenDto getById(UUID id) {

        ScreenDefinition screen =
                screenRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Screen not found: " + id
                                ));

        return ScreenDto.from(screen);
    }

    /**
     * Lấy Screen theo Permission Code
     */
    public ScreenDto getByPermissionCode(
            String permissionCode
    ) {

        ScreenDefinition screen =
                screenRepository.findByPermissionCode(permissionCode)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Screen not found: "
                                                + permissionCode
                                ));

        return ScreenDto.from(screen);
    }

    public ScreenDto getByScreenCode(
        String screenCode
        ){

        ScreenDefinition screen =
                screenRepository.findByScreenCode(screenCode)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Screen not found: "
                                        + screenCode
                                )
                        );


        return ScreenDto.from(screen);
        }
}