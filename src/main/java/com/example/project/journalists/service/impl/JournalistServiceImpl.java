package com.example.project.journalists.service.impl;

import com.example.project.journalists.exception.AlreadyExistsException;
import com.example.project.journalists.exception.DaoException;
import com.example.project.journalists.exception.ResourceNotFoundException;
import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.CategoryDto;
import com.example.project.journalists.model.dto.JournalistDto;
import com.example.project.journalists.model.entity.Category;
import com.example.project.journalists.model.entity.Journalist;
import com.example.project.journalists.model.mapper.JournalistMapper;
import com.example.project.journalists.repository.JournalistRepository;
import com.example.project.journalists.service.JournalistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalistServiceImpl implements JournalistService {

    private final JournalistRepository journalistRepository;
    private final JournalistMapper journalistMapper;

    public  JournalistServiceImpl(JournalistRepository journalistRepository, JournalistMapper journalistMapper){
        this.journalistRepository = journalistRepository;
        this.journalistMapper = journalistMapper;
    }

    @Override
    public List<JournalistDto> getAllJournalists() throws ServiceException {
        try{
            List<Journalist> journalists = journalistRepository.findAll();
            List<JournalistDto> journalistDtos = new ArrayList<>();
            for(Journalist journalist: journalists){
                JournalistDto journalistDto = journalistMapper.convertToDto(journalist);
                journalistDtos.add(journalistDto);
            }
            return journalistDtos;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public JournalistDto getJournalistById(long id) throws ServiceException {
        try{
            Optional<Journalist> optionalJournalist = journalistRepository.findById(id);
            if(optionalJournalist.isEmpty()){
                throw new ResourceNotFoundException("Journalist","id",id);
            }
            Journalist journalist = optionalJournalist.get();
            return journalistMapper.convertToDto(journalist);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public JournalistDto createJournalist(JournalistDto journalistDto) throws ServiceException {
        try{
            Journalist journalist = journalistMapper.convertToEntity(journalistDto);
            Journalist createdJournalist =  journalistRepository.insert(journalist);
            return journalistMapper.convertToDto(createdJournalist);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteJournalist(long id) throws ServiceException {
        try{
            Optional<Journalist> optionalJournalist = journalistRepository.findById(id);
            if(optionalJournalist.isEmpty()){
                throw new ResourceNotFoundException("Journalist","id",id);
            }
            return journalistRepository.remove(id);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public JournalistDto updateJournalist(long id, JournalistDto journalistDto) throws ServiceException {
        try{
            Optional<Journalist> optionalJournalist = journalistRepository.findById(id);
            if(optionalJournalist.isEmpty()){
                throw new ResourceNotFoundException("Journalist","id",id);
            }
            Journalist journalist = journalistMapper.convertToEntity(journalistDto);
            Journalist updatedJournalist = journalistRepository.update(id, journalist);
            return journalistMapper.convertToDto(updatedJournalist);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
