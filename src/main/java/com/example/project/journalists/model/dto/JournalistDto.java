package com.example.project.journalists.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class JournalistDto extends BaseDto{
    private String name;
    private String surname;
    private String photoURL;
    private String description;
}
