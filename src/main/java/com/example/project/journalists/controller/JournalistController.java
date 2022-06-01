package com.example.project.journalists.controller;

import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.JournalistDto;
import com.example.project.journalists.service.JournalistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journalists")
public class JournalistController {

    private final JournalistService journalistService;

    public JournalistController(JournalistService journalistService){
        this.journalistService = journalistService;
    }

    @GetMapping
    public List<JournalistDto> getJournalists() throws ServiceException{
        return journalistService.getAllJournalists();
    }

    @GetMapping("/{id}")
    public JournalistDto getJournalistBuId(@PathVariable("id") long id) throws ServiceException{
        return journalistService.getJournalistById(id);
    }

    @PostMapping
    public  JournalistDto createJournalist(@RequestBody JournalistDto journalistDto) throws ServiceException{
        return journalistService.createJournalist(journalistDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteJournalist(@PathVariable("id") long id) throws ServiceException{
        return journalistService.deleteJournalist(id);
    }

    @PutMapping("/{id}")
    public  JournalistDto updateJournalist(@PathVariable("id") long id, @RequestBody JournalistDto journalistDto) throws ServiceException{
        return journalistService.updateJournalist(id, journalistDto);
    }
}
