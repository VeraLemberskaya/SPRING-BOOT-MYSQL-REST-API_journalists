package com.example.project.journalists.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto extends BaseDto{
    private String title;
    private String content;
    private LocalDate date;
    private JournalistDto journalist;
    private List<CategoryDto> categories;
}
