package com.example.project.journalists.service;

import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.JournalistDto;

import java.util.List;

public interface JournalistService {
    List<JournalistDto> getAllJournalists() throws ServiceException;
    JournalistDto getJournalistById(long id) throws ServiceException;
    JournalistDto createJournalist(JournalistDto journalistDto) throws ServiceException;
    boolean deleteJournalist(long id) throws ServiceException;
    JournalistDto updateJournalist(long id, JournalistDto journalistDto) throws ServiceException;
}
