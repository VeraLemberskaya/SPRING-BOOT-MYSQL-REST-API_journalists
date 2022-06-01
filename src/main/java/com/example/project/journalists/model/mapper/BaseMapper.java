package com.example.project.journalists.model.mapper;

import com.example.project.journalists.model.dto.BaseDto;
import com.example.project.journalists.model.entity.BaseEntity;

public interface BaseMapper <D extends BaseDto, E extends BaseEntity>{
    D convertToDto(E e);
    E convertToEntity(D d);
}
