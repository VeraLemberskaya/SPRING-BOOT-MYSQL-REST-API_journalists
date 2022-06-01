package com.example.project.journalists.model.mapper;

import com.example.project.journalists.model.dto.JournalistDto;
import com.example.project.journalists.model.entity.Journalist;
import org.springframework.stereotype.Component;

@Component
public class JournalistMapper implements BaseMapper<JournalistDto, Journalist> {
    @Override
    public JournalistDto convertToDto(Journalist journalist) {
        return JournalistDto.builder()
                .id(journalist.getId())
                .name(journalist.getName())
                .surname(journalist.getSurname())
                .photoURL(journalist.getPhotoURL())
                .description(journalist.getDescription())
                .build();
    }

    @Override
    public Journalist convertToEntity(JournalistDto journalistDto) {
        return Journalist.builder()
                .id(journalistDto.getId())
                .name(journalistDto.getName())
                .surname(journalistDto.getSurname())
                .photoURL(journalistDto.getPhotoURL())
                .description(journalistDto.getDescription())
                .build();
    }
}
